/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ronaldo Hanh
 * Class kết nối databse 
 */
public class ConnectDatabase {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_itss";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    /**
     *
     */
    public static Connection con = getConnect();
    public static PreparedStatement pst;
    public static ResultSet rs;

    /**
     * Hàm kết nối đến cơ sở dữ liệu
     * Thông báo kết nối thất bại nếu có exception 
     * @return 
     */
    public static Connection getConnect(){
        try {
            if(con != null) con.close();
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connect success!"); 
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Ket noi that bai");
        }
        return con;
    }
    
    /**
     * Hàn thực hiện câu truy vấn
     * Hướng dấn dùng:
        ResultSet rs = connect.query(sql);
        try {
            while(rs.next()){ //rs.next() sẽ duyệt các kết quả cho đến khi không còn kết quả nào
                System.out.println(rs.getString("Name")); //hàm rs.getString nhận đầu vào là tên cột hoặc số thứ tự côt, trả ra dữ liệu của cột đó.
            }
        } catch (Exception e) {
        * 
        }
     * @param sql
     * @return giá trị có kiểu là ResultSet 
     */
    public static ResultSet excuteQuery(String sql){
        rs = null;
        pst = null;
        try {
           pst = con.prepareStatement(sql);
           rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("query error: " + e.toString());
        } 
        return rs;
    }
}
