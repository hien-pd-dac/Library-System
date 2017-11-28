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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
     * Functon validateInfo() validate thông tin trong Session
     * @return true nếu validate
     * @return  false nếu not validate
     */
    public boolean validateInfo() {
        if (Session.get("userIDIssueCard") == null) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã người vay.");
            return false;
        } else {
            if (Session.get("activationCode") == null) {
                JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã kích hoạt.");
                return false;
            }
            else if(Session.get("activationCode").length() < 6){
                JOptionPane.showMessageDialog(null, "Mã kích hoạt phải có tối thiểu 6 kí tự!");
                return false;
            }
            else {
                if (validateExpireDay() < 0) {
                    JOptionPane.showMessageDialog(null, "Ngày hết hạn phải lớn hơn ngày hiện tại.");
                    return false;
                }
                return true;
            }
        }
    }
    
    /**
     * Function insertToDB() chèn dữ liệu vào database 
     */
    public void insertToDB() {
        //ConnectDatabase connect = new ConnectDatabase("tdd", "root", "");
        ConnectDatabase.getConnect();
        String checkExistUser = "SELECT * from borrower where UserID = ?";
        try {
            ConnectDatabase.pst = ConnectDatabase.con.prepareStatement(checkExistUser);
            ConnectDatabase.pst.setInt(1, Integer.parseInt(Session.get("userIDIssueCard")));
            ConnectDatabase.rs = ConnectDatabase.pst.executeQuery();
            if (ConnectDatabase.rs.next()) {
                if(checkExisted() == false){
                    insert();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Người vay đã có thẻ.");
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Không tồn tại người vay.");
            }
        } catch (Exception e) {
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
            JOptionPane.showMessageDialog(null, "Tạo thẻ thành công!");
        } catch (Exception e) {
        }
    }

    /**
     * Function checkExisted() kiểm tra UserID đã có thẻ vay hay chưa
     * @return true nếu có thẻ vay
     * @return false nếu chưa có thẻ vay
     */
    private boolean checkExisted(){
        int kt = 0;
        Connection con1 = ConnectDatabase.con;
        PreparedStatement stmt1;
        ResultSet rs1;
        String sql = "Select count(`UserID`) from card where UserID = ?";
        try {
            stmt1 = con1.prepareStatement(sql);
            stmt1.setInt(1, Integer.parseInt(Session.get("userIDIssueCard")));
            rs1 = stmt1.executeQuery();
            if(rs1.next()){
                kt = rs1.getInt(1);
            }
        } catch (Exception e) {
        }
        if(kt >= 1 ){
            return true;
        }
        return false;
    }

    /**
     * Functiin validateExpireDay kiểm tra ngày hết hạn
     * @return 1 nếu ngày hết hạn > ngày hiện tại
     * @return  -1 nếu ngày hết hạn <= ngày hiện tại 
     */
    private int validateExpireDay() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int date = Calendar.getInstance().get(Calendar.DATE);

        if (Integer.parseInt(Session.get("year")) > year) {
            return 1;
        } else if (Integer.parseInt(Session.get("year")) == year) {
            if (Integer.parseInt(Session.get("month")) > month) {
                return 1;
            } else if (Integer.parseInt(Session.get("month")) < month) {
                return -1;
            } else {
                if (Integer.parseInt(Session.get("date")) > date) {
                    return 1;
                } else {
                    return -1;
                }
            }
        } else if (Integer.parseInt(Session.get("year")) < year) {
            return -1;
        }
        return -1;
    }

}
