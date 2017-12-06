/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.borrowers;

import com.library.controllers.BaseController;
import com.library.controllers.MainController;
import com.library.helpers.Session;
import com.library.models.*;
import com.library.utils.Utils;
import static com.library.utils.Utils.*;
import com.library.views.borrowers.BookCartView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author hpd
 */
public class BookCartController implements BaseController {
    private final BookCartView bookCartView;
    
    public BookCartController() {
        bookCartView = new BookCartView();
        setTableModel();
        bookCartView.setBookCartViewListener(new BookCartViewAction());
    }
    
    private void setTableModel() {
        DefaultTableModel tableModel = getTableModel();
        bookCartView.setTable(tableModel);
    }
    
    private DefaultTableModel getTableModel() {
        DefaultTableModel model;
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
        ResultSet rs; 
        rs = BookCartModel.getBookInCart();
        if(rs == null) return new DefaultTableModel();
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
        return model;
    }

    @Override
    public void hideGUI() {
        this.bookCartView.setVisible(false);
    }

    @Override
    public void showGUI() {
        setTableModel();
        this.bookCartView.setVisible(true);
    }
    /**
     * 
     * @return 1 if done, 0 if book is unavailable
     */
    private int checkSubmit() {
        TableModel tableModel = bookCartView.getTableModel();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ( ! tableModel.getValueAt(i, 7).toString().equals("0")) {
                return 0;
            }
        }
        return 1;
    }
    
    private int saveRegisterBorrow() {
        TableModel tableModel = bookCartView.getTableModel();
        int rowCount = tableModel.getRowCount();
        String[] copyID;
        copyID = new String[rowCount];
        for (int i = 0; i < rowCount; i++) {
            copyID[i] = tableModel.getValueAt(i, 0).toString();
        }
        if (0 == RegisterBorrowModel.saveRegisterBorrow(Session.get("cardID"), rowCount, copyID)) 
            return 0;
        else return 1;
    }
    
    private int deleteBooksInCart() {
        return BookCartModel.deleteBooksInCart(Session.get("cardID"));
        
    }
    
    private class BookCartViewAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()) {
                case BACK_BTN: {
                    MainController.redirect_to(BookCartController.class, BorrowerMenuController.class);
                } break;
                case REMOVE_BTN: {
                    int row = bookCartView.getSelectedRow();
                    if(row == -1) {
                        JOptionPane.showMessageDialog(null, "Select a book to remove from book cart!", 
                                    "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String copyID = bookCartView.getSelectedCopyID(row);
                        int result; 
                        result = JOptionPane.showConfirmDialog(null, "Do you want to remove this book from cart!", 
                                "Alert", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            Utils.debug("deleting (cardID, copyID) : " + Session.get("cardID") +" - "+ copyID);
                            int removeResult = BookCartModel.removeFromCart(Session.get("cardID"), copyID);
                            Utils.debug("removeResult: " + removeResult);
                            if (removeResult > 0){
                                JOptionPane.showMessageDialog(null, "Remove from cart successfully!", 
                                        "Success", JOptionPane.DEFAULT_OPTION);
                            }
                            setTableModel();
                        }
                    }
                    
                } break;
                case SUBMIT_BTN: {
                    // TODO
                    int result; 
                    result = JOptionPane.showConfirmDialog(null, "Do you want to submit!", 
                            "Alert", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        int checkSubmit = checkSubmit();
                        if (checkSubmit == 0) {
                            JOptionPane.showMessageDialog(null, "Some books are no longer available (status != 0) to borrow!", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (saveRegisterBorrow() != 0) {
                                int deleteResult = deleteBooksInCart();
                                Utils.debug("submit borrow: " + Integer.toString(deleteResult));
                                setTableModel();
                                JOptionPane.showMessageDialog(null, "Submit successfully!", 
                                            "Success", JOptionPane.DEFAULT_OPTION);
                            } else {
                                JOptionPane.showMessageDialog(null, "Some errors occurred!", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    }
                } break;
                case ADD_MORE_BTN: {
                    MainController.redirect_to(BookCartController.class, ListBookController.class);
                } break;
                default: break;
            }
        }
        
    }
}
