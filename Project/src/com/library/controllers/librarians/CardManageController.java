/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.librarians;

import com.library.controllers.*;
import com.library.views.librarians.CardManageView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ronaldo Hanh
 */
public class CardManageController implements BaseController {

    private CardManageView cardManageView;

    public CardManageController() {
        this.cardManageView = new CardManageView();
        cardManageView.addButtonBackToMenuLibrarianListener(new LibrarianManageListener());
        cardManageView.addButtonIssueCardListener(new CardIssueListener());
        cardManageView.addButtonSearchCardListener(new CardSearchListener());
    }

    @Override
    public void hideGUI() {
        cardManageView.setVisible(false);
    }

    @Override
    public void showGUI() {
        cardManageView.setVisible(true);
    }

    class LibrarianManageListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(CardManageController.class, LibrarianManageController.class);
        }

    }

    class CardIssueListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(CardManageController.class, CardIssueController.class);
        }
    }

    class CardSearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(CardManageController.class, CardSearchController.class);
        }
    }
}
