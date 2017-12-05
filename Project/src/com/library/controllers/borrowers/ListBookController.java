/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.borrowers;

import com.library.controllers.BaseController;
import com.library.controllers.MainController;
import com.library.models.BookModel;
import static com.library.utils.Utils.*;
import com.library.views.borrowers.ListBookView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author hpd
 */
public class ListBookController implements BaseController {
    private final ListBookView listBookView;
    private BookModel bookModel;
    
    public ListBookController () {
        listBookView = new ListBookView();
        setDataTable();
        listBookView.setListBookViewListerner(new ListBookViewAction());
    }
    
    private void setDataTable() {
        listBookView.setTable(getTableModel());
    }
    
    private DefaultTableModel getTableModel() {
        JTable dataTable = new JTable();
        DefaultTableModel model;
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
        ResultSet rs; 
        rs = BookModel.getAllBook();
        try {
            ResultSetMetaData rsMD = rs.getMetaData();
            int colNumber = rsMD.getColumnCount();
            String[] arr = new String[colNumber];
            for (int i = 0; i < colNumber; i++) {
                arr[i] = rsMD.getColumnName(i+1);
            }
            model.setColumnIdentifiers(arr);
            while(rs.next()) {
                for (int i = 0; i < colNumber; i++) {
                    arr[i] = rs.getString(i+1);
                }
                model.addRow(arr);
            }
            
            
        } catch (SQLException e) {
            
        }
        dataTable.setModel(model);
        
//        return dataTable;
        return model;
    }
    
    private class ListBookViewAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case BACK_BTN: {
                    MainController.redirect_to(ListBookController.class, BorrowerMenuController.class);
                } break;
                case SEARCH_BTN: {
                    // VIET HUNG TODO
                } break;
                case ADD_TO_CART_BTN: {
                    // TODO HIEN
                    
                } break;
                default: break;
            }
        }
        
    }

    @Override
    public void hideGUI() {
        listBookView.setVisible(false);
//    listBookView.dispose();
    }

    @Override
    public void showGUI() {
        listBookView.setVisible(true);
    }
    
}
