/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;
import controller.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * class CardIssueView có nhiệm vụ tạo giao diện phát hành thẻ mới.
 * @author Ronaldo Hanh
 */
public class CardIssueView extends javax.swing.JFrame implements ActionListener{
    /**
     * @attribute cardIssueForm là form đăng ký 
     * @attribute lbBorrowerID là label mã người mượn
     * @attribute lbExpirationDate là label ngày hết hạn
     * @attribute lbBorrowerName là label tên người mượn
     * @attribute lbActivationCode là label mã kích hoạt
     * @attribute cbBorrowerID là 1 checkbox cho phép chọn mã 
     * @attribute tfBorrowerName là 1 textfield để nhập tên người mượn
     * @attribute rbRole là 1 radio button để chọn vai trò của người mượn
     * @attribute cbDay là 1 checkbox chọn ngày hết hạn thẻ
     * @attribute cbMonth là 1 checkbox chọn tháng hết hạn thẻ
     * @attribute cbYear là 1 checkbox chọn năm hết hạn thẻ
     * @attribute tfActivationCode là 1 textfield tương ứng với mã thẻ đã kích hoạt( không sửa được)
     * @attribute btnCardIssue là 1 button thực hiện phát hành thẻ mới
     * @attribute cardIssueController là 1 object của lớp CardIssueController
     * @attribute card là 1 object của lớp Card
     */
    private JPanel cardIssueForm;
    private JLabel lbBorrowerID;
    private JLabel lbBorrowerName;
    private JLabel lbExpirationDate;
    private JLabel lbActivationCode;
    private JCheckBox cbBorrowerID;
    private JTextField tfBorrowerName;
    private JRadioButton rbRole;
    private JCheckBox cbDay;
    private JCheckBox cbMonth;
    private JCheckBox cbYear;
    private JTextField tfActivationCode;
    private JButton btnCardIssue;
    private CardIssueController cardIssueController;
    private Card card;

    /**
     * method setCardIssueController để khởi tạo đối tượng cardIssueController của lớp CardIssueController
     * @param cardIssueController là đối tượng của lớp CardIssueController
     */
    public void setCardIssueController(CardIssueController cardIssueController) {
        this.cardIssueController = cardIssueController;
    }
    
    /**
     * method setCard để khởi tạo đối tượng card của lớp Card
     * @param card là đối tượng của lớp Card
     */
    public void setCard(Card card) {
        this.card = card;
    }
    
    
    
    
    /**
     * Constructor khởi tạo UI cho lớp 
     */
    public CardIssueView(){
        this.add(cardIssueForm);
        this.add(lbBorrowerID);
        this.add(lbBorrowerName);
        this.add(lbExpirationDate);
        this.add(lbActivationCode);
        this.add(cbBorrowerID);
        this.add(tfBorrowerName);
        this.add(rbRole);
        this.add(cbDay);
        this.add(cbMonth);
        this.add(cbYear);
        this.add(tfActivationCode);
        btnCardIssue.addActionListener(this);
        this.setVisible(false);
    }

     /**
    * ghi đè phương thức trong interface ActionListener
    * bắt sự kiện khi click vào button btnCardIssue
    */
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == btnCardIssue){
          cardIssueController.issueNewCard(card);
       }
    }
}
