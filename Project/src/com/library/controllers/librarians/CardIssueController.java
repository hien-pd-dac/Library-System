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

    /**
     * Hàm khởi tạo
     */
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

    /**
     * Class CardIssueListener lắng nghe sự kiên khi click vào button phát hành
     * thẻ
     */
    class CardIssueListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            saveInfoToSession();
            card.insert();
            removeSession();
        }

        /**
         * Function saveInfoToSession lưu dữ liệu nhận được từ view vào Sesion
         */
        private void saveInfoToSession() {
            String date = cardIssueView.getYear() + "-" + cardIssueView.getMonth() + "-" + cardIssueView.getDay();
            Session.add("userIDIssueCard", cardIssueView.getUserID());
            Session.add("activationCode", cardIssueView.getActivationCode());
            Session.add("expiredDate", date);
            Session.add("year", cardIssueView.getYear());
            Session.add("month", cardIssueView.getMonth());
            Session.add("date", cardIssueView.getDay());
        }

//        /**
//         * Funtion checkInfo validate thông tin đã nhập
//         * @return true nếu thông tin đã nhập validate
//         * @return false nếu thông tin đã nhập not validate
//         */
//        private int checkInfo() {
//            if (card.validateInfo(Session.get("date"), Session.get("month"), Session.get("year")) == true) {
//                System.out.println("Du lieu validate OK");
//                return 1;
//            } else {
//                System.out.println("Du lieu validate Fail");
//                return 0;
//            }
//        }
        /**
         * Function removeSession xóa dữ liệu ở Session khi không dùng nữa
         */
        private void removeSession() {
            Session.destroy();
        }
    }

    /**
     * Class CardIssueReturnListener lắng nghe sự kiện khi click vào button quay
     * lại
     */
    class CardIssueReturnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(CardIssueController.class, CardManageController.class);
        }

    }
}
