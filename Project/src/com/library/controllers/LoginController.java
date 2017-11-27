/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers;

import com.library.controllers.borrowers.BorrowerMenuController;
import com.library.controllers.librarians.LibrarianManageController;
import com.library.helpers.Session;
import com.library.models.UserModel;
import static com.library.utils.Utils.LOGIN_BTN;
import com.library.views.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
                    JOptionPane.showMessageDialog(null, "Không bỏ trống các trường!");
                } else {
                    try {
                        String username = loginView.getUsernameField();
                        String password = loginView.getPasswordField();
                        int checkRole = UserModel.login(username, password);
                        if(checkRole != -1) {
                            setSessionUser(username, password, checkRole);
                            if (checkRole == 1) {
                                MainController.redirect_to(LoginController.class, LibrarianManageController.class);
                            } else if (checkRole == 2) {
                                MainController.redirect_to(LoginController.class, BorrowerMenuController.class);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Username hoặc mật khẩu không đúng!", 
                                    "Login false", JOptionPane.ERROR_MESSAGE);
                            System.out.println("login false!");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        private boolean isInvalidInput() {
            return (loginView.getUsernameField().equals("") 
                    || loginView.getPasswordField().equals(""));
        }

        private void setSessionUser(String username, String password, int role) {
            Session.add("username", username);
            Session.add("password", password);
            Session.add("role", Integer.toString(role));
        }
    }
}
