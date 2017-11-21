/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.views.guests;

/**
 *
 * @author hpd
 */
public class LoginController {
    private LoginView loginView;

    public LoginController(String title) {
        initView(title);
        setLoginViewAction();
    }
    private void setLoginViewAction() {
        loginView.setLoginListener(new LoginView.LoginListener() {
            @Override
            public void onClickLogin() {
                loginView.loginBtn.setVisible(false);
            }

            @Override
            public void onclickRegister() {
                
            }
        });
    }
    private void initView(String title) {
        loginView = new LoginView(title);
    }
    
    public static void main(String[] args) {
        LoginController loginController = new LoginController("LoginController");
        loginController.loginView.setVisible(true);
    }
}
