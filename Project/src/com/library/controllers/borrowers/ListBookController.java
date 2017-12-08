/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.borrowers;

import com.library.controllers.BaseController;
import com.library.controllers.MainController;
import com.library.helpers.Session;
import com.library.models.BookCartModel;
import com.library.models.BookModel;
import com.library.models.CardModel;
import com.library.models.RegisterBorrowedModel;
import com.library.utils.Utils;
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
    private ListBookView listBookView;
    private BookModel bookModel;
    
    public ListBookController () {
        if(Session.get("bookIDSearching") == null)
            Session.add("bookIDSearching", "all");
        listBookView = new ListBookView();
        setDataTable();
        listBookView.setListBookViewListerner(new ListBookViewAction());
    }
    
    private void setDataTable() {
        listBookView.setTable(getTableModel());
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
        rs = BookModel.getListBookResult();
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
                    String bookIDSearching = listBookView.getTextInput();
                    if (bookIDSearching.equals("")) {
                        Session.remove("bookIDSearching");
                        Session.add("bookIDSearching", "all");
                    } else {
                        Session.remove("bookIDSearching");
                        Session.add("bookIDSearching", bookIDSearching);
                    }
                    setDataTable();
                    
                } break;
                case ADD_TO_CART_BTN: {
                    // TODO HIEN
                    int row = listBookView.getSelectedRow();
                    if(row == -1) {
                        JOptionPane.showMessageDialog(null, "Select a book to add to bookcart!", 
                                    "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (CardModel.isExpired(Session.get("cardID")) != 0) {
                            JOptionPane.showMessageDialog(null, "Card is Expired!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (RegisterBorrowedModel.hasOverUnreturned(Session.get("cardID")) != 0) {
                            JOptionPane.showMessageDialog(null, "You have Over Unreturned Book!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            int numBookInCart = BookCartModel.getNumberOfBookInCart();
                            int numBookRegisterd;
                            numBookRegisterd = RegisterBorrowedModel.getNumberOfBookRegistered(Session.get("cardID"));
                            if (numBookInCart + numBookRegisterd >= 5) {
                                JOptionPane.showMessageDialog(null, numBookInCart+" in Cart, "+ numBookRegisterd+" registerd."
                                        + "Can't add anymore!", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                String bookID = listBookView.getSelectedBookID(row);
            //                    System.out.println(bookID);
                                int minCopyID = BookModel.getMinCopyID(bookID);
                                if(minCopyID <= 0) {
                                    JOptionPane.showMessageDialog(null, "Book is not available!", 
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                } else if (BookCartModel.numberAlreadyInCart(bookID) > 0) {
                                    JOptionPane.showMessageDialog(null, "Book was already in your cart!", 
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    int addResult = BookCartModel.addToCart(Session.get("cardID"), Integer.toString(minCopyID));
                                    if (addResult > 0){
                                        JOptionPane.showMessageDialog(null, "Add to cart successfully!", 
                                                "Success", JOptionPane.DEFAULT_OPTION);
                                    }
                                }
                            }
                        }
                        
                        
                    }
                } break;
                case BOOK_CART_BTN: {
                    MainController.redirect_to(ListBookController.class, BookCartController.class);
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
        setDataTable();
        listBookView.setVisible(true);
    }
    public static void main(String[] args) {
        MainController.redirect_to(null, ListBookController.class);
    }
}
