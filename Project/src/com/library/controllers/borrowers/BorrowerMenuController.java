/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.borrowers;

import com.library.controllers.BaseController;
import com.library.views.borrowers.*;

/**
 *
 * @author hpd
 */
public class BorrowerMenuController implements BaseController {
    private BorrowerMenuView borrowerMenuView;
    
    
    public BorrowerMenuController () {
        borrowerMenuView = new BorrowerMenuView();
        
    }
    
//    public static void main(String[] args) {
//        BorrowerMenuController borrowerMenuController = new BorrowerMenuController();
//        borrowerMenuController.borrowerMenuView.setVisible(true);
//    }
//    

    @Override
    public void hideGUI() {
        borrowerMenuView.setVisible(false);
    }

    @Override
    public void showGUI() {
        borrowerMenuView.setVisible(true);
    }
}
