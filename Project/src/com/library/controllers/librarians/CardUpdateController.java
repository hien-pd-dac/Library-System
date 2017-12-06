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
 * Controller quản lí chức năng cập nhật thông tin thẻ
 * @author Ronaldo Hanh
 */
public class CardUpdateController implements BaseController{

    private CardUpdateView view;
    private CardModel card;
    /**
     * Hàm khởi tạo 
     */
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
    
    /**
     * Lớp lắng nghe sự kiện khi click vào button cập nhật thông tin thẻ
     */
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
        
        /**
         * Hàm kiểm tra ngày hết hạn có hợp lệ hay không
         * @return 1 nếu ngày hết hạn > ngày hiện tại
         * @return 0 nếu ngày hết hạn <= ngày hiện tại
         */
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
    
    /**
     * Lớp lắng nghe sự kiện click vào button quay lại
     */
    class ButtonQuayLaiClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(CardUpdateController.class, CardDetailController.class);
        }       
    }
}
