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

/**
 *
 * @author hpd
 */
public class BookCartModel {
    public static ResultSet getBookInCart() {
        String sqlCommand = "SELECT c.BookID, b.copy_sequence, c.tittle, c.publisher, c.author, c.ISBN, b.status\n" +
                            "FROM bookcart AS a\n" +
                            "JOIN copyofbook AS b ON a.copyID = b.copyID\n" +
                            "JOIN book AS c ON c.BookID = b.BookID\n" +
                            "WHERE cardID = ?";
        ResultSet rs;
        PreparedStatement pst;
        try {
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, Session.get("cardID"));
            System.out.println(Session.get("cardID"));
            rs = pst.executeQuery();
            if(!rs.next()) {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("error userIsExist!");
            return null;
        }
        return rs;
    }
}
