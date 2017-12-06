/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.borrowers;

import com.library.controllers.BaseController;
import com.library.controllers.LoginController;
import com.library.controllers.MainController;
import com.library.helpers.Session;
import static com.library.utils.Utils.*;
import com.library.views.borrowers.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author hpd
 */
public class BorrowerMenuController implements BaseController {
    private final BorrowerMenuView borrowerMenuView;
    
    
    public BorrowerMenuController () {
        borrowerMenuView = new BorrowerMenuView();
        borrowerMenuView.setBorrowerMenuViewListener(new BorrowerMenuViewAction());
    }
    
    private class BorrowerMenuViewAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case SEARCH_BOOK_BTN: {
                    MainController.redirect_to(BorrowerMenuController.class, ListBookController.class);
                } break;
                case BOOK_CART_BTN: {
                    // TODO
                } break;
                case BORROWED_HISTORY_BTN: {
                    // TODO
                } break;
                case ACCOUNT_BTN: {
                    // TODO
                } break;
                case LOGOUT_BTN: {
                    // TODO
                    clearUserSession();
                    MainController.redirect_to(BorrowerMenuController.class, LoginController.class);
                } break;
                
                default: break;
            } 
        }
    }
    
    private void clearUserSession() {
        Session.remove("username");
        Session.remove("password");
        Session.remove("role");
    }
    @Override
    public void hideGUI() {
        borrowerMenuView.setVisible(false);
    }

    @Override
    public void showGUI() {
        borrowerMenuView.setVisible(true);
    }
}
