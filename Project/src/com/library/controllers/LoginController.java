/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers;

import com.library.controllers.borrowers.BorrowerMenuController;
import com.library.helpers.Session;
import static com.library.utils.Utils.LOGIN_BTN;
import com.library.views.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author hpd
 */
public class LoginController implements BaseController {
    private LoginView loginView;
    
    public LoginController () {
        loginView = new LoginView();
        loginView.addLoginViewAction(new LoginViewAction());
    }
    
    
    
    @Override
    public void hideGUI() {
        loginView.setVisible(false);
    }

    @Override
    public void showGUI() {
        loginView.setVisible(true);
    }

    private class LoginViewAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(LOGIN_BTN)) {
                if (isInvalidInput()) {
                    System.out.println("invalid!");
                } else {
//                    setSessionUser();
                    MainController.redirect_to(LoginController.class, BorrowerMenuController.class);
                }
            }
        }

        private boolean isInvalidInput() {
            return (loginView.getUsernameField().equals("") 
                    || loginView.getPasswordField().equals(""));
        }

        private void setSessionUser() {
            Session.add("username", loginView.getUsernameField());
            Session.add("password", loginView.getPasswordField());
        }
    }
}
