/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.models;

import com.library.helpers.ConnectDatabase;
import com.library.helpers.Session;
import static com.library.models.BookCartModel.getMaxRegisterBorrow;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hpd
 */
public class RegisterBorrowedModel {
    
    /**
     * 
     * @param cardID
     * @param rowCount
     * @param copyID
     * @return 0 if false, 1 if success
     */
    public static int saveRegisterBorrow(String cardID, int rowCount, String[] copyID) {
        
        String sqlCommand = "INSERT INTO registerborrow(registerID, cardID, registerAt, returnAt) \n"
                + "VALUES (NULL, ?, ?, ?)";
        String sqlCommand1 = "INSERT INTO registerborrowline(registerID, copyID) \n"
                + "VALUES (?, ?) ";
        ResultSet rs;
        PreparedStatement pst;
        try {
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, cardID);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c;
            c = Calendar.getInstance();
            c.setTime(new Date());
            pst.setString(2, dateFormat.format(c.getTime()));
            c.add(Calendar.DATE, 14);
            pst.setString(3, dateFormat.format(c.getTime()));
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
     * @return >0 if notReturn, 0 if returned, -1 if error
     */
    public static int getNumberOfBookRegistered(String cardID) {
        String sqlCommand = "SELECT COUNT(*) AS count FROM registerborrow NATURAL JOIN registerborrowline\n"+
                            "WHERE cardID = ? AND (status = 0 OR status = 2)";
        ResultSet rs;
        PreparedStatement pst;
        try {
//            ConnectDatabase.getConnect();
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, cardID);
            rs = pst.executeQuery();
            if(rs.next()){
                return Integer.parseInt(rs.getString("count"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    /**
     * 
     * @param cardID
     * @return the number of book is over unreturned
     */
    public static int hasOverUnreturned(String cardID) {
        String sqlCommand = "SELECT COUNT(*) AS count "
                + "FROM registerborrow NATURAL JOIN registerborrowline"
                + " WHERE cardID = ? AND status = 2 AND `returnAt` < ?";
        ResultSet rs;
        PreparedStatement pst;
        try {
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            pst.setString(1, cardID);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            pst.setString(2, dateFormat.format(date));
            rs = pst.executeQuery();
            if(rs.next()){
                return Integer.parseInt(rs.getString("count"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public static ResultSet getRegisterBorrowed(String cardID) {
        String sqlCommand;
        if (cardID.equals("all")) {
            sqlCommand = "SELECT a.registerID, a.cardID, a.registerAt, a.returnAt, "
                    + "c.BookID, c.copy_sequence, d.title, c.status\n" +
                        "FROM registerborrow as a\n" +
                        "NATURAL JOIN registerborrowline as b\n" +
                        "NATURAL JOIN copyofbook as c\n" +
                        "NATURAL JOIN book as d\n"
                    + "ORDER BY a.registerAt DESC";
        } else {
            sqlCommand = "SELECT a.registerID, a.cardID, a.registerAt, a.returnAt, "
                    + "c.BookID, c.copy_sequence, d.title, c.status\n" +
                    "FROM registerborrow as a\n" +
                    "NATURAL JOIN registerborrowline as b\n" +
                    "NATURAL JOIN copyofbook as c\n" +
                    "NATURAL JOIN book as d\n"
                    + "WHERE a.cardID = ? \n"
                    + "ORDER BY a.registerAt DESC";
        }
        
        ResultSet rs;
        PreparedStatement pst;
        try {
//            ConnectDatabase.getConnect();
            pst = ConnectDatabase.con.prepareStatement(sqlCommand);
            if ( ! cardID.equals("all")) {
                pst.setString(1, cardID);
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
        } catch (SQLException e) {
            System.out.println("error query!");
            return null;
        }
    }
}
