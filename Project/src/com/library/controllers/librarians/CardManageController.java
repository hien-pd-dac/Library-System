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
 * Class CardManageController điều khiển CardManageView
 * @author Ronaldo Hanh
 */
public class CardManageController implements BaseController {

    private CardManageView cardManageView;

    /**
     * Hàm khởi tạo
     */
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

    /**
     * Lớp LibrarianManageListener lắng nghe sự kiện khi click vào button quay lại
     */
    class LibrarianManageListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(CardManageController.class, LibrarianManageController.class);
        }

    }
    /**
     * Lớp CardIssueListener lắng nghe sự kiện khi click vào button phát hành thẻ
     */
    class CardIssueListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(CardManageController.class, CardIssueController.class);
        }
    }

    /**
     * Lớp CardSearchListener lắng nghe sự kiện khi click vào button Tìm kiếm thẻ
     */
    class CardSearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(CardManageController.class, CardSearchController.class);
        }
    }
}
