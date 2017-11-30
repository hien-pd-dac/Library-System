/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.borrowers;

import com.library.views.borrowers.ListBookView;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author hpd
 */
public class ListBookController {
    private ListBookView listBookView;
    
    public ListBookController () {
        listBookView = new ListBookView();
        setDataTable();
    }
    
    private void setDataTable() {
        JTable dataTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = this.getBookData(); 
        try {
            ResultSetMetaData rsMD = rs.getMetaData();
            int colNumber = rsMD.getColumnCount();
            String[] arr = new String[colNumber];
            for (int i = 0; i < colNumber; i++) {
                
            }
        } catch (SQLException e) {
            
        }
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private ResultSet getBookData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
