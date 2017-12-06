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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hpd
 */
public class BookCartModel {
    public static ResultSet getBookInCart() {
        String sqlCommand = "SELECT b.copyID, c.BookID, b.copy_sequence, c.title, c.publisher, c.author, c.ISBN, b.status \n" +
                            "FROM bookcart AS a\n" +
                            "NATURAL JOIN copyofbook AS b \n" +
                            "NATURAL JOIN book AS c \n" +
                            "WHERE cardID = ? \n"+ 
                            "ORDER BY b.copyID ASC";
        ResultSet rs;
        PreparedStatement pst;
        try {
//            ConnectDatabase.getConnect();
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, Session.get("cardID"));
            System.out.println(Session.get("cardID"));
            rs = pst.executeQuery();
            int rowcount = 0;
            if (rs.last()) {
              rowcount = rs.getRow();
              rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            if (rowcount == 0) return null;
            else
                return rs;
        } catch (SQLException e) {
            System.out.println("error query!");
            return null;
        }
    }
    /**
     * 
     * @param bookID
     * @return < 0 if error, 0 if not in cart, > 0 if already in cart.
     */
    public static int numberAlreadyInCart(String bookID) {
        String sqlCommand = "SELECT COUNT(*) AS count FROM bookcart "
                            + "NATURAL JOIN copyofbook WHERE cardID = ? and BookID = ?";
        ResultSet rs;
        PreparedStatement pst;
        try {
//            ConnectDatabase.getConnect();
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, Session.get("cardID"));
            pst.setString(2, bookID);
//            System.out.println(Session.get("cardID"));
            rs = pst.executeQuery();
            if(rs.next()) {
                return Integer.parseInt(rs.getString("count"));
            } 
        } catch (SQLException e) {
            System.out.println("error query");
            return -1;
        }
        return -1;
    }
    /**
     * 
     * @param cardID
     * @param copyID
     * @return number of row be affected
     */
    public static int addToCart(String cardID, String copyID) {
        String sqlCommand = "INSERT INTO `bookcart` (`bookCartID`, `cardID`, `copyID`) "
                + "VALUES (NULL, ?, ?)";
        ResultSet rs;
        PreparedStatement pst;
        try {
//            ConnectDatabase.getConnect();
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, cardID);
            pst.setString(2, copyID);
            int executeResult = pst.executeUpdate();
            return executeResult;
        } catch (SQLException e) {
            System.out.println("error add to cart false");
            return -1;
        }
    }

    public static int removeFromCart(String cardID, String copyID) {
        String sqlCommand = "DELETE FROM `bookcart` WHERE `cardID` = ? AND `copyID` = ? ";
        ResultSet rs;
        PreparedStatement pst;
        try {
//            ConnectDatabase.getConnect();
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, cardID);
            pst.setString(2, copyID);
            int executeResult = pst.executeUpdate();
            return executeResult;
        } catch (SQLException e) {
            System.out.println("error add to cart false");
            return -1;
        }
    }

    public static int getNumberOfBookInCart() {
        String sqlCommand = "SELECT COUNT(*) AS count FROM bookcart WHERE cardID = ?";
        ResultSet rs;
        PreparedStatement pst;
        try {
//            ConnectDatabase.getConnect();
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, Session.get("cardID"));
            rs = pst.executeQuery();
            if(rs.next()) {
                return Integer.parseInt(rs.getString("count"));
            }
        } catch (SQLException e) {
            System.out.println("error query");
            return -1;
        }
        return -1;
    }
    
    public static int getMaxRegisterBorrow() {
        String sqlCommand = "SELECT registerID FROM registerborrow ORDER BY registerID DESC limit 1";
        ResultSet rs;
        PreparedStatement pst;
        try {
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            rs = pst.executeQuery();
            if(rs.next()) {
                return Integer.parseInt(rs.getString("registerID"));
            } else 
                return 0;
        } catch (SQLException e) {
            System.out.println("error query");
            return -1;
        }
    }
    /**
     * 
     * @param cardID
     * @param rowCount
     * @param copyID
     * @return 0 if false, 1 if success
     */
    public static int saveRegisterBorrow(String cardID, int rowCount, String[] copyID) {
        
        String sqlCommand = "INSERT INTO registerborrow(registerID, cardID, registerAt, status) \n"
                + "VALUES (NULL, ?, ?, 0)";
        String sqlCommand1 = "INSERT INTO registerborrowline(registerID, copyID) \n"
                + "VALUES (?, ?) ";
        ResultSet rs;
        PreparedStatement pst;
        try {
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, cardID);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            System.out.println(dateFormat.format(date)); 
            pst.setString(2, dateFormat.format(date));
            if (pst.executeUpdate() <= 0) return 0;
            
            int maxRegisterID = getMaxRegisterBorrow();
            pst = ConnectDatabase.con.prepareStatement(sqlCommand1);
            pst.setString(1, Integer.toString(maxRegisterID));
            for (int i = 0; i < rowCount; i++) {
                pst.setString(2, copyID[i]);
                if (pst.executeUpdate() <= 0) return 0;
            }
            
        } catch (SQLException e) {
            System.out.println("error save register false");
            return 0;
        }
        return 1;
    }

    /**
     * 
     * @param cardID
     * @return number of rows be affected
     */
    public static int deleteBooksInCart(String cardID) {
        String sqlCommand = "DELETE FROM `bookcart` WHERE `cardID` = ?";
        PreparedStatement pst;
        try {
//            ConnectDatabase.getConnect();
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, cardID);
            int executeResult = pst.executeUpdate();
            return executeResult;
        } catch (SQLException e) {
            System.out.println("error add to cart false");
            return -1;
        }
    }
}
