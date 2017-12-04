/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.test.hien;

import com.library.controllers.LoginController;
import com.library.controllers.MainController;
import com.library.controllers.borrowers.ListBookController;
import com.library.helpers.ConnectDatabase;
import com.library.models.BookModel;

/**
 *
 * @author hpd
 */
public class Hien_Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        MainController.redirect_to(null, LoginController.class);
//        ListBookController listBookController = new ListBookController();
//        listBookController.showGUI();
    }
}
