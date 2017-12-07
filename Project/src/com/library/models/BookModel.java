/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.models;

import com.library.helpers.ConnectDatabase;
import com.library.helpers.Session;
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
    
    public static ResultSet getListBookResult() {
        String sqlCommand;
        if( Session.get("bookIDSearching").equals("all")) {
            sqlCommand = "SELECT * FROM book";
        } else {
            sqlCommand = "SELECT * FROM book WHERE BookID = ? ";
        }
        
        ResultSet rs = null;
        PreparedStatement pst;
        try {
//            ConnectDatabase.getConnect();
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            if ( ! Session.get("bookIDSearching").equals("all")) {
                pst.setString(1, Session.get("bookIDSearching"));
            }
            rs = pst.executeQuery();
            int rowcount = 0;
            if (rs.last()) {
              rowcount = rs.getRow();
              rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            if (rowcount == 0) return null;
            else
                return rs;
            
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
