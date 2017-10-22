package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import model.BookCart;
/**
    * Created by Pham Duc Hien on 21/10/2017
    * class SearchBorrowInfoView create UI of search borrow book Info
*/

public class SearchBorrowInfoView extends JPanel implements ActionListener {
    /**
    * attribute searchBorrowInfoController : dùng để gọi đến các hành động của controller.
    */
    private SearchBorrowInfoController searchBorrowInfoController;

    /**
    * attribute searchInput gồm các text input khác nhau
    */
    private JPanel searchInput;

    /**
    * attribute resultTable: result of searching.
    */
    private JPanel resultTable;

    /**
    * attribute dateRegister: date registered
    */
    private JTextField dateRegister;

    /**
    * attribute cardID: card id input
    */
    private JTextField cardID;

    /**
    * attribute userName: username input
    */
    private JTextField userName;

    /**
    * attribute searchBtn: click btn to search
    */
    private JButton searchBtn;

    /**
    * attribute detailBtns: click to view detail of borrow info.
    */
    private JButton detailBtns[20];

    /**
    * contructor khởi tạo UI cho lớp
    */
    public SearchBorrowInfoView(){
        searchBorrowInfoController = new searchBorrowInfoController();
        this.setLayout();
        this.add(searchInput);
        searchInput.setLayout();
        searchInput.add(dateRegister);
        searchInput.add(cardID);
        searchInput.add(userName);
        this.add(resultTable);
        for( i=0; i<n; i++){
            resultTable.add(detailBtns[i]);
            detailBtns[i].addActionListener(this);
        }
        this.add(searchBtn);
        searchBtn.addActionListener(this);
        setVisible(false);
    }

    /**
    * method renderTable hiển thị danh sách thông tin các giỏ hàng đã đăng kí.
    * @param bookCarts là danh sách các giỏ hàng tìm được.
    */
    private void renderTable(BookCart[] bookCarts) {
        for(i=0; i<bookCarts.count; i++) {
            this.resultTable.show(bookCarts[i]);
        }
    }

    /**
    * ghi đè phương thức trong interface ActionListener
    * @param ActionListener e là hành động lắng nghe sự kiện của người dùng
    */
    @Override
    public void actionPerformed(ActionListener e){
        switch(e.getSource()){
            case searchBtn: {
                date = this.dateRegister.getValue();
                cardID = this.cardID.getValue();
                userName = this.userName.getValue()
                BookCart[] bookCarts = new BookCart[20];
                bookCarts = searchBorrowInfoController.getBookCart(date, cardID, userName);
                renderTable(bookCarts);
                break;
            }
            case detailBtns[i]: {
                BookCart bookCart = new BookCart();
                bookCart = detailBtns[i].getBookCartInfo();
                searchBorrowInfoController.goToDetailBorrowView(bookCart);
                break;
            }
            case default:
                break;
        }
    }
}
