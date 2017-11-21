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

public class GuestFrameView extends JFrame {
    
    private JPanel headerPanel;
    private JPanel contentPanel;
    private JButton logoBtn;
    private JLabel tvLabel;
    public JButton loginBtn;
    public JButton signupBtn;
    
    /**
     *
     * @param title
     */
    public GuestFrameView(String title) {
        super(title);
        initComponents();
    }
    
    public void setContentPanel(JPanel contentPanel) {
        this.add(contentPanel, BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        GuestFrameView guestFrame;
        guestFrame = new GuestFrameView("GuestFrame");
        guestFrame.setVisible(true);
    }
    private void initComponents() {
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLayout(new BorderLayout());
        
        headerPanel = new JPanel();
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        headerPanel.setSize(WINDOW_WIDTH, HEADER_HEIGHT);
        BorderLayout headerLayout = new BorderLayout();
        headerPanel.setLayout(headerLayout);
        logoBtn = new JButton();
        
        Image image = new ImageIcon(getClass().getResource(
                "../../resources/logo_bk.png")).getImage();
        Icon icon = new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        logoBtn.setIcon(icon);
        
        tvLabel = new JLabel("Thu vien TQB", Label.LEFT);
        tvLabel.setFont(tvLabel.getFont().deriveFont(30f));
        
        loginBtn = new JButton("Login");
//        loginBtn.setActionCommand("loginBtn");
        signupBtn = new JButton("Signup");
//        signupBtn.setActionCommand("signupBtn");
        
        JPanel logoPanel = new JPanel(new FlowLayout());
        logoPanel.add(logoBtn);
        logoPanel.add(tvLabel);
        JPanel authPanel = new JPanel(new FlowLayout());
        authPanel.add(loginBtn);
        authPanel.add(signupBtn);
        
        headerPanel.add(logoPanel, BorderLayout.LINE_START);
        headerPanel.add(authPanel, BorderLayout.LINE_END);
        
//        contentPanel = new JPanel();
//        contentPanel.setSize(WINDOW_WIDTH, CONTENT_HEIGHT);
        
        this.add(headerPanel, BorderLayout.NORTH);
//        this.add(contentPanel);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
    }
    

}
