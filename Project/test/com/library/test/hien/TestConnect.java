/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.test.hien;

import com.library.helpers.ConnectDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ronaldo Hanh
 * Class kết nối databse 
 */
public class TestConnect {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost:3306/";
    private String dbName;
    private String username;
    private String password;
    private Connection connection;
    private Statement stmt;
    
    /**
     * Constructor 
     * @param dbName là tên daatabase 
     * @param username là tên người dùng, thường là "root"
     * @param password là mật khẩu, thường là ""
     */
    public TestConnect() {
        
    }
    public TestConnect(String dbName, String username, String password){
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
            connection = DriverManager.getConnection(DB_URL, username, password);
            System.out.println("Connect success!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Ket noi that bai");
        }
    }
    
    private void showData(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.printf("%10s %10s %10s\n", rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("show false!");
        }
    }
    
    private ResultSet query (String sqlCommand) {
        ResultSet rs = null;
        stmt = null;
        try {
            stmt = ConnectDatabase.con.createStatement();
            rs = stmt.executeQuery(sqlCommand);
        } catch (SQLException e) {
            System.out.println("error" + e.toString());
        }
        return rs;
    }
    
    
    
    public static void main(String[] args) {
        TestConnect tc = new TestConnect();
        tc.showData(tc.query("select * from user"));
        
    }
}
