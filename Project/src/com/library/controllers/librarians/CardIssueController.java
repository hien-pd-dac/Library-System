/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.librarians;

import com.library.controllers.BaseController;
import com.library.controllers.MainController;
import com.library.helpers.Session;
import com.library.models.CardModel;
import com.library.views.librarians.CardIssueView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Ronaldo Hanh
 */
public class CardIssueController implements BaseController {

    private CardIssueView cardIssueView;
    private CardModel card;

    public CardIssueController() {
        card = new CardModel();
        cardIssueView = new CardIssueView();
        cardIssueView.addButtonIssueCardListener(new CardIssueListener());
        cardIssueView.addButtonReturnListener(new CardIssueReturnListener());

    }

    @Override
    public void hideGUI() {
        cardIssueView.setVisible(false);
    }

    @Override
    public void showGUI() {
        cardIssueView.setVisible(true);
    }

    class CardIssueListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            saveInfoToSession();
            if (checkInfo() == 1) {
                card.insertToDB();
            }
            removeSession();
        }

        private void saveInfoToSession() {
            String date = cardIssueView.getYear() + "-" + cardIssueView.getMonth() + "-" + cardIssueView.getDay();
            Session.add("userIDIssueCard", cardIssueView.getUserID());
            Session.add("activationCode", cardIssueView.getActivationCode());
            Session.add("expiredDate", date);
            Session.add("year", cardIssueView.getYear());
            Session.add("month", cardIssueView.getMonth());
            Session.add("date", cardIssueView.getDay());
            //JOptionPane.showMessageDialog(null, "userID: " + Session.get("userIDIssueCard")+ "activationCode: " + Session.get("activationCode")+ "expriedDate: "+ Session.get("expiredDate"));
            //System.out.println("userID: " + Session.get("userID")+ "activationCode: " + Session.get("activationCode")+ "expriedDate: "+ Session.get("expiredDate"));
        }

        private int checkInfo() {
            if (card.validateInfo() == true) {
                System.out.println("Du lieu validate OK");
                return 1;
            } else {
                System.out.println("Du lieu validate Fail");
                return 0;
            }
        }

        private void removeSession() {
            Session.destroy();
        }
    }

    class CardIssueReturnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(CardIssueController.class, CardManageController.class);
        }

    }
}
