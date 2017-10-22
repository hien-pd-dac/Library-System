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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * class CardUpdateView có nhiệm vụ thực thi phần tạo giao diện cập nhật thông tin thẻ
 * @author Ronaldo Hanh
 */
public class CardUpdateView extends javax.swing.JFrame implements ActionListener {
    /**
     * @attribute lbBorrowerName là label tên người mượn
     * @attribute lbExpirationDate là label ngày hết hạn
     * @attribute cbDay là 1 checkbox chọn ngày hết hạn thẻ
     * @attribute cbMonth là 1 checkbox chọn tháng hết hạn thẻ
     * @attribute cbYear là 1 checkbox chọn năm hết hạn thẻ
     * @attribute tfBorrowerName là 1 textfield để nhập tên người mượn
     * @attribute rbRole là 1 radio button để chọn vai trò của người mượn
     * @attribute btnCardUpdate là 1 button thực hiện cập nhật thông tin thẻ
     * @attribute cardIssueController là 1 object của lớp CardIssueController
     * @attribute card là 1 object của lớp Card
     */
    private JLabel borrowerName;
    private JLabel lbExpirationDate;
    private JCheckBox cbDay;
    private JCheckBox cbMonth;
    private JCheckBox cbYear;
    private JTextField tfBorrowerName;
    private JRadioButton rbRole;
    private JButton btnCardUpdate;
    private Card card;
    private CardUpdateController cardUpdateController;
    
    /**
     * method setCardIssueController để khởi tạo đối tượng cardIssueController của lớp CardIssueController
     * @param cardUpdateController là đối tượng của lớp CardUpdateController
     */
    public void setCardUpdateController(CardUpdateController cardUpdateController) {
        this.cardUpdateController = cardUpdateController;
    }
    
    /**
     * method setCard để khởi tạo đối tượng card của lớp Card
     * @param card là đối tượng của lớp Card
     */
    public void setCard(Card card) {
        this.card = card;
    }
    /**
     * Method khởi tạo UpdateView UI
     */
    public CardUpdateView(){
        this.add(borrowerName);
        this.add(lbExpirationDate);
        this.add(cbDay);
        this.add(cbMonth);
        this.add(cbYear);
        this.add(tfBorrowerName);
        this.add(rbRole);
        this.add(btnCardUpdate);
        //do something...
    }

      /**
    * ghi đè phương thức trong interface ActionListener
    * bắt sự kiện khi click vào button btnCardUpdate
    */
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == btnCardUpdate){
          cardUpdateController.updateCard(card);
       }
    }
}
