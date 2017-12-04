/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.models;

import com.library.helpers.ConnectDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hpd
 */
public class UserModel {

    
    private String userID;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private int gender;
    private int contact;
    
    /**
     * 
     * @param username
     * @param password
     * @return -1 if false, 0 if admin, 2 borrower, 1 if librarian
     * @throws java.sql.SQLException
     */
    public static int login(String username, String password) throws SQLException {
        String sqlCommand = "SELECT COUNT(*) as cot from user WHERE username = ? and password = ?";
        ResultSet rs;
        PreparedStatement pst;
        try {
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("error userIsExist!");
            return -1;
        }
        rs.next();
        if(rs.getInt("cot") == 0) return -1;
        else {
            String sqlCommand1 = "SELECT role from user where username = ?";
            pst = ConnectDatabase.con.prepareStatement(sqlCommand1);
            pst.setString(1, username);
            rs = pst.executeQuery();
            rs.next();
            return rs.getInt("role");
        }
    }
    
    public static int getUserID(String username) {
        String sqlCommand = "SELECT UserID from user WHERE username = ?";
        ResultSet rs;
        PreparedStatement pst;
        try {
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if (!rs.next()) {
                return -1;
            } else {
                return rs.getInt("UserID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
            return -2;
        }
    }
    
    
    public static int getCardID(String userID) {
        String sqlCommand = "SELECT CardID FROM card WHERE UserID = ?";
        ResultSet rs;
        PreparedStatement pst;
        try {
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, userID);
            rs = pst.executeQuery();
            if (!rs.next()) {
                return -1;
            } else {
                return rs.getInt("CardID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
            return -2;
        }
        
    }
    
    public static void main(String[] args) throws SQLException {
            System.out.println(UserModel.login("1234567c", "123456"));
        
    }
    
}
