package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import model.Card;

/**
    * Created by Pham Duc Hien on 21/10/2017
    * class RegisterBorrowBookView có nhiệm vụ tạo giao diện đăng kí mượn sách.
*/

public class RegisterBorrowBookView extends JPanel implements ActionListener {
    /**

    */
    private RegisterBorrowBookController registerBorrowBookController;

    /**
    * attribute tableListBook hiển thị danh sách các sách trong giỏ đăng kí.
    */
    private JPanel tableListBook;

    /**
    * attribute addBookBtn là 1 button thực hiện thêm sách vào giỏ.
    */
    private JButton addBookBtn;

    /**
    * attribute submitBtn là 1 button thực hiện submit các cuốn sách trong giỏ để đăng kí mượn.
    */
    private JButton submitBtn;

    /**
    * contructor khởi tạo UI cho lớp
    */
    public RegisterBorrowBookView(Card card){
        this.registerBorrowBookController = new registerBorrowBookController(card);
        this.setLayout();
        this.add(tableListBook);
        this.add(addBookBtn);
        addBookBtn.addActionListener(this);
        this.add(submitBtn);
        submitBtn.addActionListener(this);
        setVisible(false);
    }


    /**
    * ghi đè phương thức trong interface ActionListener
    */
    @Override
    public void actionPerformed(ActionListener e){
        switch(e.getSource()){
            case addBookBtn: {
                this.setVisible(false)
                registerBorrowBookController.goToSearchBookView();
                break;
            }

            case submitBtn: {
                if(registerBorrowBookController.submit() == true) {
                    notify("Submit successfully!");
                } else {
                    notify("Submit false!");
                }
            }
        }
    }
}
