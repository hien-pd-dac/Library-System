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
public class BookModel {
    private String bookID;
    private String title;
    private String publisher;
    private String author;
    private String ISBN;
    
    public static ResultSet getAllBook() {
        String sqlCommand = "SELECT * FROM book";
        ResultSet rs = null;
        PreparedStatement pst;
        try {
//            ConnectDatabase.getConnect();
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            rs = pst.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    /**
     * 
     * @param bookID
     * @return 0 if not borrowable, >0 is maxCopyID, < 0 if error
     */
    public static int getMinCopyID(String bookID) {
        String sqlCommand = "SELECT copyID FROM copyofbook "
                + "WHERE BookID = ? AND typeOfCopy = '1' AND status = '0' "
                + "ORDER BY copyID ASC LIMIT 1";
        ResultSet rs;
        PreparedStatement pst;
        try {
//            ConnectDatabase.getConnect();
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, bookID);
            rs = pst.executeQuery();
            if(rs.next()){
                return Integer.parseInt(rs.getString("copyID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    
}
