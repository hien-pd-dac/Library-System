/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers;

import com.library.helpers.Session;
import com.library.views.CardIssueView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Ronaldo Hanh
 */
public class CardIssueController implements BaseController {

    private CardIssueView cardIssueView;

    public CardIssueController() {
        cardIssueView = new CardIssueView();
        cardIssueView.addButtonIssueCardListener(new CardIssueListener());
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
        }

        public void saveInfoToSession() {
            String date = "" + cardIssueView.getDay() + "/" + cardIssueView.getMonth() + "/" + cardIssueView.getYear();
            Session.add("userIDIssueCard", cardIssueView.getUserID());
            Session.add("activationCode", cardIssueView.getActivationCode());
            Session.add("expiredDate", date);
            //JOptionPane.showMessageDialog(null, "userID: " + Session.get("userIDIssueCard")+ "activationCode: " + Session.get("activationCode")+ "expriedDate: "+ Session.get("expiredDate"));
            //System.out.println("userID: " + Session.get("userID")+ "activationCode: " + Session.get("activationCode")+ "expriedDate: "+ Session.get("expiredDate"));
        }
    }

}
