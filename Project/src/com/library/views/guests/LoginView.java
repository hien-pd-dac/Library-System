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
import javax.swing.*;
import java.awt.*;

import static com.library.utils.Utils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class LoginView extends GuestFrameView implements ActionListener{
    private JPanel contentPanel;
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel loginL;
    private JLabel usernameL;
    private JLabel passwordL;
    private JButton submitBtn;
    private JLabel notiL;
    
    private LoginListener loginListener;
    
    
    
    public LoginView (String title) {
        super(title);
        initComponents();
    }
    
    public static void main(String[] args) {
        LoginView loginView = new LoginView("Login");
        loginView.setVisible(true);
        
    }
    
    private void initFrame() {
        loginBtn.addActionListener(this);
        loginBtn.setActionCommand("loginBtn");
        signupBtn.addActionListener(this);
        signupBtn.setActionCommand("signupBtn");
    }
    
    private void initComponents() {
        initFrame();
        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 15));
        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 15));
        loginL = new JLabel("Login");
        System.out.println(loginL.getFont().toString());
        loginL.setFont(new Font("Dialog", Font.BOLD, 30));
        usernameL = new JLabel("User name:", Label.LEFT);
        usernameL.setFont(new Font("Dialog", Font.PLAIN, 15));
        
        passwordL = new JLabel("Password:", Label.LEFT);
        passwordL.setFont(new Font("Dialog", Font.PLAIN, 15));
        
        notiL = new JLabel("noti");
        notiL.setVisible(false);
        notiL.setFont(new Font("Dialog", Font.PLAIN, 15));
        submitBtn = new JButton("Login");
        
        contentPanel = new JPanel(new FlowLayout());  // khung chua content
        contentPanel.setSize(CONTENT_WIDTH, CONTENT_HEIGHT);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JPanel panel = new JPanel(new GridBagLayout()); // box login
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        panel.setBorder(BorderFactory.createLineBorder(Color.red));
        gbc.gridx = 0;
        gbc.gridy = 0;
//        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(loginL, gbc);
        
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(usernameL, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(usernameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordL, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);
        
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(notiL, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(submitBtn, gbc);
        panel.setBackground(Color.decode("#D8D8D8"));
        
        contentPanel.setBorder(new EmptyBorder(100, 10, 10, 10));
//        contentPanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 10));
        contentPanel.add(panel);
        
        this.setContentPanel(contentPanel);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "loginBtn": {
                loginListener.onClickLogin();
            } break;
            case "signupBtn" : {
                loginListener.onclickRegister();
            } break;
            default: break;
        }
    }
    
    public interface LoginListener {
        void onClickLogin();
        void onclickRegister();
    }
    
    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }
    
}
