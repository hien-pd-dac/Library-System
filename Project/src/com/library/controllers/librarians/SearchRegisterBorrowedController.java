/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.librarians;

/**
 *
 * @author hpd
 */
import com.library.controllers.BaseController;
import com.library.controllers.LoginController;
import com.library.controllers.MainController;
import com.library.helpers.Session;
import com.library.models.*;
import com.library.utils.Utils;
import static com.library.utils.Utils.*;
import com.library.views.borrowers.BookCartView;
import com.library.views.librarians.SearchRegisterBorrowedView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SearchRegisterBorrowedController implements BaseController {
    private SearchRegisterBorrowedView searchRegisterBorrowedView;
    
    public SearchRegisterBorrowedController() {
        Session.add("cardIDSearching", "all");
        searchRegisterBorrowedView = new SearchRegisterBorrowedView();
        setTableModel();
        searchRegisterBorrowedView.setSearchRegisBorrListener(new SearchRegisterAction());
    }
    
    /**
     * 
     * @param cardID if null is get all
     */
    private void setTableModel() {
        String cardID = Session.get("cardIDSearching");
        DefaultTableModel tableModel = getTableModel(cardID);
        searchRegisterBorrowedView.setTable(tableModel);
    }
    
    private DefaultTableModel getTableModel(String cardID) {
        DefaultTableModel model;
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
        ResultSet rs; 
        rs = RegisterBorrowedModel.getRegisterBorrowed(cardID);
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
    
    private class SearchRegisterAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()) {
                case SEARCH_BTN: {
                    String cardIDSearching;
                    cardIDSearching = searchRegisterBorrowedView.getTextInput();
                    Session.remove("cardIDSearching");
                    if (cardIDSearching.equals("")) {
                        Session.add("cardIDSearching", "all");
                    } else {
                        Session.add("cardIDSearching", cardIDSearching);
                    }
                    setTableModel();
                } break;
                
                case BACK_BTN: {
                    MainController.redirect_to(SearchRegisterBorrowedController.class,
                            LibrarianManageController.class);
                } break;
                default: break;
            }
        }
    }

    @Override
    public void hideGUI() {
        searchRegisterBorrowedView.setVisible(false);
    }

    @Override
    public void showGUI() {
        setTableModel();
        searchRegisterBorrowedView.setVisible(true);
    }
    
    public static void main(String[] args) {
        SearchRegisterBorrowedController controller = new SearchRegisterBorrowedController();
        controller.showGUI();
    }

    
}
