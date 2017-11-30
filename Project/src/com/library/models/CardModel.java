/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.models;

import com.library.helpers.ConnectDatabase;
import com.library.helpers.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author hnv Model card
 * @attribute userCode
 * @attribute expiredDate;
 * @attribute activationCode
 * @method getter and setter
 */
public class CardModel {

    private String userCode;
    private Date expiredDate;
    private String activationCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    /**
     * Function insert() gọi đến lớp CardInsert để thực hiện insert
     */
    public void insert() {
        CardInsert card = new CardInsert();
    }

    /**
     * Functiin validateExpireDay kiểm tra ngày hết hạn
     *
     * @return 1 nếu ngày hết hạn > ngày hiện tại
     * @return -1 nếu ngày hết hạn <= ngày hiện tại
     */
    public int validateExpireDay(String day1, String month1, String year1) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int date = Calendar.getInstance().get(Calendar.DATE);

        if (Integer.parseInt(year1) > year) {
            return 1;
        } else if (Integer.parseInt(year1) == year) {
            if (Integer.parseInt(month1) > month) {
                return 1;
            } else if (Integer.parseInt(month1) < month) {
                return -1;
            } else {
                if (Integer.parseInt(day1) > date) {
                    return 1;
                } else {
                    return -1;
                }
            }
        } else if (Integer.parseInt(year1) < year) {
            return -1;
        }
        return -1;
    }

    /**
     * Class quản lý việc ohats hành thẻ mới
     */
    class CardInsert {

        public CardInsert(){
            if(validateInfo(Session.get("date"), Session.get("month"), Session.get("year")) == true){
                insertToDB();
            }
        }
        /**
         * Function insertToDB() kiểm tra dữ liệu trước khi chèn dữ liệu vào
         * database
         */
        private void insertToDB() {
            //ConnectDatabase connect = new ConnectDatabase("tdd", "root", "");
            ConnectDatabase.getConnect();
            String checkExistUser = "SELECT * from borrower where UserID = ?";
            try {
                ConnectDatabase.pst = ConnectDatabase.con.prepareStatement(checkExistUser);
                ConnectDatabase.pst.setInt(1, Integer.parseInt(Session.get("userIDIssueCard")));
                ConnectDatabase.rs = ConnectDatabase.pst.executeQuery();
                if (ConnectDatabase.rs.next()) {
                    if (checkExisted() == false) {
                        insert();
                    } else {
                        JOptionPane.showMessageDialog(null, "Người vay đã có thẻ.");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Không tồn tại người vay.");
                }
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Không tồn tại người vay.");
            }
        }

        /**
         * Function insert() chèn dữ liệu vào DB khi đã đủ điều kiện insert
         */
        private void insert() {
            Connection con1 = ConnectDatabase.con;
            PreparedStatement stmt1;
            ResultSet rs1;
            String insertNewCard = "INSERT into card(`UserID`, `Activation Code`, `Expired Date`) values (?, ?, ?)";
            try {
                stmt1 = con1.prepareStatement(insertNewCard);
                stmt1.setInt(1, Integer.parseInt(Session.get("userIDIssueCard")));
                stmt1.setString(2, Session.get("activationCode"));
                String lastCrawlDate = Session.get("expiredDate");
                Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(lastCrawlDate);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                stmt1.setDate(3, sqlDate);
                
                stmt1.executeUpdate();
                System.out.println("Da den cho insert ");
                JOptionPane.showMessageDialog(null, "Tạo thẻ thành công!");
            } catch (Exception e) {
            }
        }

        /**
         * Function checkExisted() kiểm tra UserID đã có thẻ vay hay chưa
         *
         * @return true nếu có thẻ vay
         * @return false nếu chưa có thẻ vay
         */
        private boolean checkExisted() {
            int kt = 0;
            Connection con1 = ConnectDatabase.con;
            PreparedStatement stmt1;
            ResultSet rs1;
            String sql = "Select count(`UserID`) from card where UserID = ?";
            try {
                stmt1 = con1.prepareStatement(sql);
                stmt1.setInt(1, Integer.parseInt(Session.get("userIDIssueCard")));
                rs1 = stmt1.executeQuery();
                if (rs1.next()) {
                    kt = rs1.getInt(1);
                }
            } catch (Exception e) {
            }
            if (kt >= 1) {
                return true;
            }
            return false;
        }

        /**
         * Functon validateInfo() validate thông tin trong Session
         *
         * @return true nếu validate
         * @return false nếu not validate
         */
        public boolean validateInfo(String day, String month, String year) {
            if (Session.get("userIDIssueCard") == null) {
                JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã người vay.");
                return false;
            } else {
                if (Session.get("activationCode") == null) {
                    JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã kích hoạt.");
                    return false;
                } else if (Session.get("activationCode").length() < 6) {
                    JOptionPane.showMessageDialog(null, "Mã kích hoạt phải có tối thiểu 6 kí tự!");
                    return false;
                } else {
                    if (validateExpireDay(day, month, year) < 0) {
                        JOptionPane.showMessageDialog(null, "Ngày hết hạn phải lớn hơn ngày hiện tại.");
                        return false;
                    }
                    return true;
                }
            }
        }
    }

    /**
     * Hàm tìm kiếm thẻ theo mã số thẻ
     *
     * @return Vector data
     */
    public Vector searchFollowCardID() {
        CardSearch card = new CardSearch();
        return card.searchFollowCardID();
    }

    /**
     * Hàm tìm kiếm thẻ theo tên đăng nhập
     *
     * @return Vector data
     */
    public Vector searchFollowUserName() {
        CardSearch card = new CardSearch();
        return card.searchFollowUserName();
    }

    /**
     * Hàm tìm kiếm thẻ theo tên người dùng
     *
     * @return Vector data
     */
    public Vector searchFollowFullName() {
        CardSearch card = new CardSearch();
        return card.searchFollowFullName();
    }

    /**
     * Hàm tìm kiếm thẻ theo mã thẻ và tên đăng nhập
     *
     * @return Vector data
     */
    public Vector searchFollowCardIDUserName() {
        CardSearch card = new CardSearch();
        return card.searchFollowCardIDUserName();
    }

    /**
     * Hàm tìm kiếm thẻ theo mã số thẻ và tên người dùng
     *
     * @return Vector data
     */
    public Vector searchFollowCardIDFullName() {
        CardSearch card = new CardSearch();
        return card.searchFollowCardIDFullName();
    }

    /**
     * Hàm tìm kiếm thẻ theo tên đăng nhập và tên người dùng
     *
     * @return Vector data
     */
    public Vector searchFollowUserNameFullName() {
        CardSearch card = new CardSearch();
        return card.searchFollowUserNameFullName();
    }

    /**
     * Hàm tìm kiếm thẻ theo mã thẻ, tên đăng nhập và tên người dùng
     *
     * @return Vactor data
     */
    public Vector searchFollowAll() {
        CardSearch card = new CardSearch();
        return card.searchFollowAll();
    }

    /**
     * Hàm hiển thị tất cả thẻ
     *
     * @return Vector data
     */
    public Vector showAll() {
        CardSearch card = new CardSearch();
        return card.showAll();
    }

    /**
     * Lớp quản lí các thao tác với tìm kiếm thẻ
     */
    class CardSearch {

        /**
         * Hàm tìm kiếm thẻ theo mã số thẻ
         *
         * @return Vactor data
         */
        public Vector searchFollowCardID() {
            Vector data = new Vector();
            String sql = "Select * from card natural join user where card.CardID = ?";
            try {
                Connection con = ConnectDatabase.con;
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs;
                stmt.setInt(1, Integer.parseInt(Session.get("cardID")));
                rs = stmt.executeQuery();
                boolean check = false;
                while (rs.next()) {
                    check = true;
                    Vector user = new Vector();
                    user.addElement(rs.getString(2));
                    user.addElement(rs.getString(4));
                    user.addElement(rs.getString(5));
                    user.addElement(rs.getString(7));
                    System.out.println(user);
                    data.add(user);
                }
                if (check == false) {
                    JOptionPane.showMessageDialog(null, "Không tồn tại mã số thẻ!");
                }
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Không tồn tại mã số thẻ!");
            }
            return data;
        }

        /**
         * Function tìm kiếm thẻ theo tên đăng nhập
         *
         * @return Vactor data
         */
        public Vector searchFollowUserName() {
            Vector data = new Vector();
            String sql = "Select * from card natural join user where user.username = ?";
            try {
                Connection con = ConnectDatabase.con;
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs;
                stmt.setString(1, (Session.get("userName")));
                //System.out.println("aaaadsafdsf");
                rs = stmt.executeQuery();
                boolean check = false;
                while (rs.next()) {
                    check = true;
                    Vector user = new Vector();
                    user.addElement(rs.getString(2));
                    user.addElement(rs.getString(4));
                    user.addElement(rs.getString(5));
                    user.addElement(rs.getString(7));
                    System.out.println(user);
                    data.add(user);
                }
                if (check == false) {
                    JOptionPane.showMessageDialog(null, "Không tồn tại thẻ!");
                }
            } catch (Exception e) {
            }
            return data;
        }

        /**
         * Hàm tìm kiếm thẻ theo họ tên người dùng
         *
         * @return Vactor data
         */
        public Vector searchFollowFullName() {
            Vector data = new Vector();
            String sql = "Select * from card natural join user where user.fullname = ?";
            try {
                Connection con = ConnectDatabase.con;
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs;
                stmt.setString(1, (Session.get("fullName")));
                rs = stmt.executeQuery();
                boolean check = false;
                while (rs.next()) {
                    check = true;
                    Vector user = new Vector();
                    user.addElement(rs.getString(2));
                    user.addElement(rs.getString(4));
                    user.addElement(rs.getString(5));
                    user.addElement(rs.getString(7));
                    System.out.println(user);
                    data.add(user);
                }
                if (check == false) {
                    JOptionPane.showMessageDialog(null, "Không tồn tại thẻ!");
                }
            } catch (Exception e) {
            }
            return data;
        }

        /**
         * Hàm tìm kiếm thoe mã số thẻ và tên đăng nhập
         *
         * @return Vector data
         */
        public Vector searchFollowCardIDUserName() {
            Vector data = new Vector();
            String sql = "Select * from card natural join user where card.CardID = ? and user.username = ?";
            try {
                Connection con = ConnectDatabase.con;
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs;
                stmt.setInt(1, Integer.parseInt(Session.get("cardID")));
                stmt.setString(2, Session.get("userName"));
                rs = stmt.executeQuery();
                boolean check = false;
                while (rs.next()) {
                    check = true;
                    Vector user = new Vector();
                    user.addElement(rs.getString(2));
                    user.addElement(rs.getString(4));
                    user.addElement(rs.getString(5));
                    user.addElement(rs.getString(7));
                    System.out.println(user);
                    data.add(user);
                }
                if (check == false) {
                    JOptionPane.showMessageDialog(null, "Không tồn tại thẻ!");
                }
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Không tồn tại thẻ!");
            }
            return data;
        }

        /**
         * Hàm tìm kiếm thẻ theo mã số thẻ và tên người mượn
         *
         * @return Vector data
         */
        public Vector searchFollowCardIDFullName() {
            Vector data = new Vector();
            String sql = "Select * from card natural join user where card.CardID = ? and user.fullname = ?";
            try {
                Connection con = ConnectDatabase.con;
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs;
                stmt.setInt(1, Integer.parseInt(Session.get("cardID")));
                stmt.setString(2, Session.get("fullName"));
                rs = stmt.executeQuery();
                boolean check = false;
                while (rs.next()) {
                    check = true;
                    Vector user = new Vector();
                    user.addElement(rs.getString(2));
                    user.addElement(rs.getString(4));
                    user.addElement(rs.getString(5));
                    user.addElement(rs.getString(7));
                    System.out.println(user);
                    data.add(user);
                }
                if (check == false) {
                    JOptionPane.showMessageDialog(null, "Không tồn tại thẻ!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Không tồn tại thẻ!");
            }
            return data;
        }

        /**
         * Hàm tìm kiếm thẻ theo tên đăng nhập và tên người dùng
         *
         * @return Vector data
         */
        public Vector searchFollowUserNameFullName() {
            Vector data = new Vector();
            String sql = "Select * from card natural join user where user.username = ? and user.fullname = ?";
            try {
                Connection con = ConnectDatabase.con;
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs;
                stmt.setString(1, Session.get("userName"));
                stmt.setString(2, Session.get("fullName"));
                rs = stmt.executeQuery();
                boolean check = false;
                while (rs.next()) {
                    check = true;
                    Vector user = new Vector();
                    user.addElement(rs.getString(2));
                    user.addElement(rs.getString(4));
                    user.addElement(rs.getString(5));
                    user.addElement(rs.getString(7));
                    System.out.println(user);
                    data.add(user);
                }
                if (check == false) {
                    JOptionPane.showMessageDialog(null, "Không tồn tại thẻ!");
                }
            } catch (Exception e) {
            }
            return data;
        }

        /**
         * Hàm tìm kiếm thẻ theo cả 3 tiêu chí
         *
         * @return Vector data
         */
        public Vector searchFollowAll() {
            Vector data = new Vector();
            String sql = "Select * from card natural join user where card.CardID = ? and user.username = ? and user.fullname = ?";
            try {
                Connection con = ConnectDatabase.con;
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs;
                stmt.setInt(1, Integer.parseInt(Session.get("cardID")));
                stmt.setString(2, Session.get("userName"));
                stmt.setString(3, Session.get("fullName"));
                rs = stmt.executeQuery();
                boolean check = false;
                while (rs.next()) {
                    check = true;
                    Vector user = new Vector();
                    user.addElement(rs.getString(2));
                    user.addElement(rs.getString(4));
                    user.addElement(rs.getString(5));
                    user.addElement(rs.getString(7));
                    System.out.println(user);
                    data.add(user);
                }
                if (check == false) {
                    JOptionPane.showMessageDialog(null, "Không tồn tại thẻ!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Không tồn tại thẻ!");
            }
            return data;
        }

        /**
         * Hàm hiển thị thông tin của tất cả thẻ
         *
         * @return
         */
        public Vector showAll() {
            Vector data = new Vector();
            String sql = "Select * from card natural join user";
            try {
                Connection con = ConnectDatabase.con;
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs;
                rs = stmt.executeQuery();
                boolean check = false;
                while (rs.next()) {
                    check = true;
                    Vector user = new Vector();
                    user.addElement(rs.getString(2));
                    user.addElement(rs.getString(4));
                    user.addElement(rs.getString(5));
                    user.addElement(rs.getString(7));
                    System.out.println(user);
                    data.add(user);
                }
                if (check == false) {
                    JOptionPane.showMessageDialog(null, "Không tồn tại thẻ!");
                }
            } catch (Exception e) {
            }
            return data;
        }
    }

    /**
     * Lớp Carđetail quản lý show thông tin chi tiết thẻ
     */
    class CardDetail {

        /**
         * Hàm thực thi hiển thị thông tin chi tiết thẻ
         */
        public void showByCardID() {
            String sql = "Select * from card natural join user natural join borrower where card.CardID = ?";
            try {
                Connection con = ConnectDatabase.con;
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(Session.get("IDClicked")));
                ResultSet rs;
                rs = stmt.executeQuery();
                while (rs.next()) {
                    SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdfSource.parse(rs.getString(4));
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
                    String newDate = dateFormat.format(date);
                    Session.add("ngayhethan", newDate);
                    Session.add("tennguoimuon", rs.getString(7));
                    Session.add("tentaikhoan", rs.getString(5));
                    Session.add("sodienthoai", rs.getString(10));
                    Session.add("isStudent", new Integer(rs.getInt(14)).toString());
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * Hàm getCardDetail gọi đến lớp CardDetail
     */
    public void getCardDetail() {
        CardDetail cardDetail = new CardDetail();
        cardDetail.showByCardID();
    }

    /**
     * Lớp quản lí việc update thông tin thẻ
     */
    class CardUpdate {

        /**
         * Hàm thực thi update
         */
        public void updateNewexpiredDay() {
            String sql = "Update card set `Expired Date` = ? where `CardID` = ?";
            try {
                Connection con = ConnectDatabase.con;
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(2, Integer.parseInt(Session.get("IDClicked")));
                String lastCrawlDate = Session.get("newExpiredDate");
                Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(lastCrawlDate);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                stmt.setDate(1, sqlDate);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Update thành công!");
            } catch (Exception e) {
            }
        }
    }

    /**
     * Hàm thực thi update thông tin thẻ
     */
    public void updateCardInfo() {
        CardUpdate newCard = new CardUpdate();
        newCard.updateNewexpiredDay();
    }

}
