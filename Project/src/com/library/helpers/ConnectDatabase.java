/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Ronaldo Hanh
 * Class kết nối databse 
 */
public class ConnectDatabase {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost:3306/";
    private String dbName;
    private String username;
    private String password;
    private Connection con;
    private Statement stmt;
    
    /**
     * Constructor 
     * @param dbName là tên daatabase 
     * @param username là tên người dùng, thường là "root"
     * @param password là mật khẩu, thường là ""
     */
    public ConnectDatabase(String dbName, String username, String password){
        this.dbName = dbName;
        this.username = username;
        this.password = password;
        this.DB_URL = this.DB_URL + this.dbName;
    }
    
    /**
     * Hàm kết nối đến cơ sở dữ liệu
     * Thông báo kết nối thất bại nếu có exception 
     */
    private void getConnect(){
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, username, password);
        } catch (Exception e) {
            System.out.println("Ket noi that bai");
        }
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
        }
     * @param sql
     * @return giá trị có kiểu là ResultSet 
     */
    private ResultSet query(String sql){
        this.getConnect();
        try {
           ResultSet rs;
           this.stmt = this.con.createStatement();
           rs = stmt.executeQuery(sql);
           return rs;
        } catch (Exception e) {
            return null;
        }   
    }
}
