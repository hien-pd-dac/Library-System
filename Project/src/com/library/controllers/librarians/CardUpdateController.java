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
import com.library.views.librarians.CardUpdateView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Ronaldo Hanh
 */
public class CardUpdateController implements BaseController{

    private CardUpdateView view;
    private CardModel card;
    
    public CardUpdateController(){
        view= new CardUpdateView();
        card = new CardModel();
        view.addButtonUpdateListener(new CardUpdateListener());
        view.addButtonQuayLaiListener(new ButtonQuayLaiClickListener());
    }
    @Override
    public void hideGUI() {
        view.setVisible(false);
    }

    @Override
    public void showGUI() {
      view.setVisible(true);
    }
    
    class CardUpdateListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
           String date = view.getYear() + "-" + view.getMonth() + "-" + view.getDay();
           Session.add("newDay", view.getDay());
           Session.add("newMonth", view.getMonth());
           Session.add("newYear", view.getYear());
           Session.add("newExpiredDate", date);
           if(checkNewDate() == 1){
               card.updateCardInfo();
           }
        }
        
        private int checkNewDate(){
            if (card.validateExpireDay(Session.get("newDay"), Session.get("newMonth"), Session.get("newYear")) == 1) {
                System.out.println("Du lieu validate OK");
                return 1;
            } else {
                System.out.println("Du lieu validate Fail");
                JOptionPane.showMessageDialog(null, "Ngày hết hạn phải lớn hơn ngày hiện tại!");
                return 0;
            }
        }  
    }
    
    class ButtonQuayLaiClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(CardUpdateController.class, CardDetailController.class);
        }
        
    }
}
