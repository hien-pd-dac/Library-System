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
     * @return true if success, false if false
     * @throws java.sql.SQLException
     */
    public static boolean isLoginSuccess(String username, String password) throws SQLException {
        String sqlCommand = "SELECT COUNT(*) as cot from user WHERE username = ? and password = ?";
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("error userIsExist!");
            return false;
        }
        rs.next();
        return rs.getInt("cot") == 1;
    }
    
    public static void main(String[] args) throws SQLException {
            System.out.println(UserModel.isLoginSuccess("1234567c", "123456"));
        
    }
    
}
