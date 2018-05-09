/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.librarians;
import com.library.controllers.*;
import com.library.views.librarians.LibrarianManageView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Controller quản lý các chức năng của thủ thư
 * @author Ronaldo Hanh
 */
public class LibrarianManageController implements BaseController {
    private LibrarianManageView librarianManageView;
    
    public LibrarianManageController(){
        this.librarianManageView = new LibrarianManageView();
        librarianManageView.addCardManageListener(new CardManageListener());
        librarianManageView.addBorrowedManageListener(new BorrowedManageListener());
        librarianManageView.addButtonManageBookListener(new ManageBookListener());
    }
    
    @Override
    public void hideGUI(){
        librarianManageView.setVisible(false);
    }
    
    public void showGUI(){
        librarianManageView.setVisible(true);
    }

    class ManageBookListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(LibrarianManageController.class, 
                    ManageBookController.class);
        }
    }
    
    /**
     * Lớp lắng nghe sự kiện khi click vào button quản lí muon sach
     */
    private class BorrowedManageListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(LibrarianManageController.class, 
                    SearchRegisterBorrowedController.class);
        }
    }
    
    /**
     * Lớp lắng nghe sự kiện khi click vào button quản lí thẻ
     */
    class CardManageListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(LibrarianManageController.class, CardManageController.class);
            
        }
    }
}
