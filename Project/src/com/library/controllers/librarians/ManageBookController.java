/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.librarians;

import com.library.controllers.*;
import com.library.views.librarians.ManageBookView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Viet
 */
public class ManageBookController implements BaseController {
    private   ManageBookView manageBookView ;
    public ManageBookController(){
        this.manageBookView = new ManageBookView();
        manageBookView.addButtonBackToMenuListener(new backToMenuListener());
        manageBookView.addButtonRegisterNewBookListener(new RegisterNewBookListener());

    }
    @Override
    public void hideGUI() {
        manageBookView.setVisible(false);
    }

    @Override
    public void showGUI() {
        manageBookView.setVisible(true);
    }

   

    class backToMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            MainController.redirect_to(ManageBookController.class, LibrarianManageController.class);
        }
    }

    

    

    class RegisterNewBookListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(ManageBookController.class, RegisterNewBookController.class);
        }
    }
    

}

    
