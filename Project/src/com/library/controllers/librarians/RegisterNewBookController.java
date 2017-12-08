/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.librarians;

import com.library.controllers.*;
import com.library.helpers.Session;
import com.library.models.BookModel;
import com.library.views.librarians.RegisterNewBookView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Viet
 */
public class RegisterNewBookController implements BaseController {
    private RegisterNewBookView registerNewBookView;
    private BookModel book;
    public RegisterNewBookController(){
        book = new BookModel() ; 
        registerNewBookView = new RegisterNewBookView();
        registerNewBookView.addButtonOKRegisterNewBookListener(new RegisterBookListener());
        registerNewBookView.addButtonbtnHuyRegisterNewBookListener(new HuyRegisterBookListener());
}

    @Override
    public void hideGUI() {
        registerNewBookView.setVisible(false);
    }

    @Override
    public void showGUI() {
        registerNewBookView.setVisible(true);
    }

    class HuyRegisterBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        MainController.redirect_to(RegisterNewBookController.class, ManageBookController.class);

        } 
    }

    class RegisterBookListener implements ActionListener{

       @Override
        public void actionPerformed(ActionEvent e) {
            saveInfoToSession();
            book.insert();
            removeSession(); 
            

        }    

        private void removeSession() {
            Session.destroy();
        }

        private void saveInfoToSession() {
            Session.add("BookID", registerNewBookView.getBookID());
            Session.add("author", registerNewBookView.getAuthor());
            Session.add("MaVach",registerNewBookView.getMaVach());
            Session.add("nameBook",registerNewBookView.getNameBook());
            Session.add("NhaXuatBan",registerNewBookView.getNXB());
            Session.add("SoLuong",registerNewBookView.getSoLuong());
            Session.add("GiaTien",registerNewBookView.getGiaTien());
        }
    }   
}
