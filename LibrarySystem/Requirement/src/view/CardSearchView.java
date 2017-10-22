/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;
import controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * class CardSearchView có nhiệm vụ tạo giao diện tìm kiếm thẻ
 * @author Ronaldo Hanh
 */
public class CardSearchView extends javax.swing.JFrame implements ActionListener {
    /**
    * @attribute lbBorrowerName là label tên người mượn
    * @attribute tfBorrowerName là ô nhập tên người mượn
    * @attribute btnSearch là button tìm kiếm
    * @attribute cardSearchController là  1 object của lớp CardSearchController
    * @attribute card là 1 object của lớp Card
    * @attribute cardUpdateView là 1 object của lớp CardUpdateView
     */
    private JLabel lbBorrowerName;
    private JTextField tfBorrowerName;
    private JButton btnSearchCard;
    private CardSearchController cardSearchController;
    private String borrowerName;
    private Card card;
    private CardUpdateView cardUpdateView;

    /**
     * method setCardIssuer để khởi tạo đối tượng card của lớp Card
     * @param card là đối tượng của lớp Card
     */
    public void setCard(Card card) {
        this.card = card;
    }
    
     /**
     * method setCardUpdateView để khởi tạo đối tượng cardUpdateView của lớp CardUpdateView
     * @param  cardUpdateView là đối tượng của lớp CardUpdateView
     */
    public void setCardUpdateView(CardUpdateView cardUpdateView) {
        this.cardUpdateView = cardUpdateView;
    }
    
    
    /**
     * Constructor khởi tạo UI cho lớp 
     */
    public CardSearchView(){
        this.add(lbBorrowerName);
        this.add(tfBorrowerName);
        this.add(btnSearchCard);
        //do something...
    }
    
     /**
    * ghi đè phương thức trong interface ActionListener
    * bắt sự kiện khi click vào button btnSearch
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSearchCard){
            card = cardSearchController.findCard(borrowerName);
            if(card!=null){
                this.setVisible(false);
                cardUpdateView.setVisible(true);
            }
            else{
                System.out.println("Find not found");
                //do something...
            }
            
        }
    }  
}
