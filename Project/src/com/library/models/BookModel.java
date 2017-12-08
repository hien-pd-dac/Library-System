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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
        if (Session.get("bookIDSearching") == null) {
            Session.add("bookIDSearching", "all");
        }
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
   

    public void insert() {
        BookInsert book = new BookInsert();
}
    
    class BookInsert{
        public BookInsert(){
        if ((validateInfo()==true)) {
            insertToDB();    
        }
    
}
    private boolean checkExisted() {
            int kt = 0;
            Connection con1 = ConnectDatabase.con;
            PreparedStatement stmt1;
            ResultSet rs1;
            String sql = "Select count(`BookID`) from book where BookID = ?";
            try {
                stmt1 = con1.prepareStatement(sql);
                stmt1.setInt(1, Integer.parseInt(Session.get("BookID")));
                rs1 = stmt1.executeQuery();
                if (rs1.next()) {
                    kt = rs1.getInt(1);
                }
            } catch (Exception e) {
            }
            if (kt >= 1) {
                return false;
            }
            return true;
        }
    public boolean validateInfo() {
            if (Session.get("nameBook") == null) {
                
                JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên sách.");
                return false;
            } else {
                if (Session.get("author") == null) {
                    
                    JOptionPane.showMessageDialog(null, "Bạn chưa nhập tác giả.");
                    return false;
                } else {
                    if (Session.get("BookID")== null) {
                     
                    JOptionPane.showMessageDialog(null, "bạn chưa nhập BookID");
                            
                    return false;
                    }else if (Session.get("MaVach")== null) {
                        JOptionPane.showMessageDialog(null, "bạn chưa nhập mã vạch");
                    }
                
                    }
                return true;
                }
            
        }
    private void insertToDB() {
            ConnectDatabase.getConnect();
            String checkExistUser = "SELECT * from book where BookID = ?";
            try {
                ConnectDatabase.pst = ConnectDatabase.con.prepareStatement(checkExistUser);
                ConnectDatabase.pst.setInt(1, Integer.parseInt(Session.get("BookID")));
                ConnectDatabase.rs = ConnectDatabase.pst.executeQuery();
            
                    if (checkExisted() == true) {
                        insert();
                    } else {
                      
                        JOptionPane.showMessageDialog(null, "sách đã tồn tại");                    }

                } catch (Exception e) {
            }
    }  


    private void insert() {
        
            Connection con1 = ConnectDatabase.con;
            PreparedStatement stmt1;
            ResultSet rs1;
            String insertNewBook = "INSERT into `book`(`BookID`, `title`, `publisher`, `author`, `ISBN`) values (?, ?, ?, ?, ? )";
            try {
                stmt1 = con1.prepareStatement(insertNewBook);
                stmt1.setString(1, Session.get("BookID"));
                stmt1.setString(2, Session.get("nameBook"));
                stmt1.setString(3, Session.get("NhaXuatBan"));
                stmt1.setString(4, Session.get("author"));
                stmt1.setString(5, Session.get("MaVach"));            
                
                stmt1.executeUpdate();               
                JOptionPane.showMessageDialog(null, "Đăng kí sách thành công!");
                      
            } catch (Exception e) {
            }insertCopy();
    }
      private void insertCopy() {
          int i = Integer.parseInt(Session.get("SoLuong")); int n;
          Connection con1 = ConnectDatabase.con;
            PreparedStatement stmt1;
            ResultSet rs1;
             String insertCopyOfBook = "INSERT into `copyofbook`(`copy_sequence`, `BookID`, `price`, `typeOfCopy`, `status`) values (?, ?, ?, ?, ? )";
                try{for(n=1; n<=i; n++){
                    stmt1 = con1.prepareStatement(insertCopyOfBook);
                    stmt1.setInt(1, n);
                    stmt1.setString(2, Session.get("BookID"));
                    stmt1.setInt(3, Integer.parseInt(Session.get("GiaTien")));
                    stmt1.setInt(4, 1);
                    stmt1.setInt(5, 0);
                    stmt1.executeUpdate();
                   JOptionPane.showMessageDialog(null, "Đăng kí sách thành công!");
                }
                   
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Đăng kí sách thành công!");
    }
}
}
}