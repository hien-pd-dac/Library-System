/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.test.hanh;

import com.library.controllers.*;
import com.library.controllers.librarians.LibrarianManageController;

/**
 *
 * @author Ronaldo Hanh
 */
public class Main {
    public static void main(String[] args) {
        MainController.redirect_to(null, LibrarianManageController.class);
    }
}
