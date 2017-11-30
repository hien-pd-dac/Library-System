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
import com.library.views.librarians.CardDetailView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller quản lý chức năng hiển thị thông tin chi tiết của thẻ
 * @author Ronaldo Hanh
 */
public class CardDetailController implements BaseController{

    private CardDetailView view;
    private CardModel card;
    
    public CardDetailController(){
        view = new CardDetailView();
        card = new CardModel();
        setLabelValue();
        view.addButtonCardUpdateListener(new CardUpdateButtonClickListener());
        view.addButtonQuayLaiListener(new ButtonQuayLaiListener());
    }
    @Override
    public void hideGUI() {
        view.setVisible(false);
    }

    @Override
    public void showGUI() {
        view.setVisible(true);
        setLabelValue();
    }
    
    /**
     * Hàm set giá trị cho các label trong view khi click vào 1 dòng trong bảng tìm kiếm
     */
    private void setLabelValue(){
        System.out.println("Session id clicked: " + Session.get("IDClicked"));
        card.getCardDetail();
        view.getCardID().setText(Session.get("IDClicked"));
        view.getExpiredDay().setText(Session.get("ngayhethan"));
        view.getFullName().setText(Session.get("tennguoimuon"));
        view.getUserName().setText(Session.get("tentaikhoan"));
        view.getPhone().setText(Session.get("sodienthoai"));
        if(Session.get("isStudent") == "0"){
            view.getRole().setText("Không");
        }
        else{
            view.getRole().setText("Có");
        }
    }
    
    /**
     * Lớp quản lí lắng nghe sự kiện khi click vào button Cập nhật thông tin thẻ
     */
    class CardUpdateButtonClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(CardDetailController.class, CardUpdateController.class);
        }
    }
    
    /**
     * Lớp quản lí lắng nghe sự kiện khi click vào button quay lại
     */
    class ButtonQuayLaiListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(CardDetailController.class, CardSearchController.class);
        }
        
    }
}
