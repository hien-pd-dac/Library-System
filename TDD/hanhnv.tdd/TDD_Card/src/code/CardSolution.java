/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author Ronaldo Hanh
 */
public class CardSolution implements Card {

    private CardIssueView view;
    private int[] result = new int[3];
    private boolean searchRS;
    private ConnectDatabase connectDB;

    public CardSolution(String userName, int day, int month, int year, String activateCode) {
        view = new CardIssueView();
        view.setVisible(true);
        view.setView(userName, day, month, year, activateCode);
        connectDB = new ConnectDatabase("tdd", "root", "");
    }

    @Override
    public int[] issueCardToDB() {
        //view.addBtnIssueCardListener(new CardIssueListener());
        this.afterClicked();
        return result;
    }

    public int searchCard(int... operands) {
        ResultSet rs;
        //Statement stmt;
        //Tim theo ID the
        if (operands[1] == 0 && operands[0] != 0) {
            String sql = "SELECT * FROM card where CardID = ?";
            try {
                connectDB.getConnect();
                connectDB.stmt = connectDB.con.prepareStatement(sql);
                connectDB.stmt.setInt(1, operands[0]);
                rs = connectDB.stmt.executeQuery();
                if (rs.next()) {
                    return 1;
                } else {
                    return 0;
                }
            } catch (Exception e) {
                System.out.println("Loi ket noi!");
            }
        } //Tìm tho username
        else if (operands[0] == 0 && operands[1] != 0) {
            String sql = "Select * from card, user where card.UserID = user.UserID and user.username = ?";
            try {
                connectDB.getConnect();
                connectDB.stmt = connectDB.con.prepareStatement(sql);
                connectDB.stmt.setString(1, new Integer(operands[1]).toString());
                rs = connectDB.stmt.executeQuery();
                if (rs.next()) {
                    return 1;
                } else {
                    return 0;
                }
            } catch (Exception e) {
                System.out.println("Loi ket noi");
            }
        } //Tìm theo cardID và username
        else if (operands[0] != 0 && operands[1] != 0) {
            String sql = "Select * from user, card where user.UserID = card.UserID and user.username = ? and card.CardID = ?";
            try {
                connectDB.getConnect();
                connectDB.stmt = connectDB.con.prepareStatement(sql);
                connectDB.stmt.setString(1, new Integer(operands[1]).toString());
                connectDB.stmt.setInt(2, operands[0]);
                rs = connectDB.stmt.executeQuery();
                if (rs.next()) {
                    return 1;
                } else {
                    return 0;
                }
            } catch (Exception e) {
                System.out.println("Loi ket noi");
            }

        }
        else if(operands[0] == 0 && operands[1] == 0){
            return 2;
        }
        //return searchRS;
        return 0;
    }

    public CardIssueView getView() {
        return this.view;
    }

    private void afterClicked() {
        String userName = view.getUserName();
        //System.out.println("User name: " + userName);
        String activationCode = view.getActivationCode();
        int day = Integer.parseInt(view.getDay());
        int month = Integer.parseInt(view.getMonth());
        int year = Integer.parseInt(view.getYear());
        checkCardInfo(userName, activationCode, day, month, year);
    }

    private int[] checkCardInfo(String userName, String activationCode, int day, int month, int year) {

        if (userName.equals("0")) {
            result[0] = 0;
        } else {
            result[0] = 1;
        }
        if (activationCode.equals("0")) {
            result[2] = 0;
        } else {
            result[2] = 1;
        }
        // System.out.println("Year: " + Calendar.getInstance().get(Calendar.YEAR));
        if (year > Calendar.getInstance().get(Calendar.YEAR)) {
            //System.out.println("year " + Calendar.getInstance().get(Calendar.YEAR)+1);
            result[1] = 1;
            return result;
        } else {
            if (month < Calendar.getInstance().get(Calendar.MONTH) + 1) {
                //System.out.println("month " + Calendar.getInstance().get(Calendar.MONTH) + 1);
                result[1] = 0;
            } else {
                if (day <= Calendar.getInstance().get(Calendar.DATE)) {
                    //System.out.println("Day: " + day);
                    //System.out.println("Date " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    result[1] = 0;
                } else {
                    result[1] = 1;
                }
            }
        }
        return result;
    }

    class ConnectDatabase {

        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        private String DB_URL = "jdbc:mysql://localhost:3306/";
        private String dbName;
        private String username;
        private String password;
        public Connection con;
        public PreparedStatement stmt;

        /**
         * Constructor
         *
         * @param dbName là tên daatabase
         * @param username là tên người dùng, thường là "root"
         * @param password là mật khẩu, thường là ""
         */
        public ConnectDatabase(String dbName, String username, String password) {
            this.dbName = dbName;
            this.username = username;
            this.password = password;
            this.DB_URL = this.DB_URL + this.dbName;
        }

        /**
         * Hàm kết nối đến cơ sở dữ liệu Thông báo kết nối thất bại nếu có
         * exception
         */
        private void getConnect() {
            try {
                Class.forName(JDBC_DRIVER);
                con = DriverManager.getConnection(DB_URL, username, password);
            } catch (Exception e) {
                System.out.println("Ket noi that bai");
            }
        }
    }
}
