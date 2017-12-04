/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.models;

import com.library.helpers.ConnectDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            rs = pst.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
}
