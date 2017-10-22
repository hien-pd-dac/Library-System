package controller;

import javax.swing.*;
import java.awt.event.ActionListener;
import model.BookCart;
import view.DetailBorrowView;
/**
    * Created by Pham Duc Hien on 21/10/2017
    * class SearchBorrowInfoController excute actions for SearchBorrowInfoView
*/
public class SearchBorrowInfoController {

    /**
    * method getBookCart trả về mảng các giỏ hàng đã đăng kí mượn
    */
    public BookCart[] getBookCart(Date date, String cardID, String userName) {
        return BookCart.getBookCart(date, cardID, userName);
    }

    /**
    * method goToDetailBorrowView redirect tới giao diện thông tin mượn sách chi tiết.
    * @param bookCart : thông tin chi tiết về giỏ hàng.
    */
    public void goToDetailBorrowView(BookCart bookCart) {
        DetailBorrowView detailBorrowView = new DetailBorrowView(bookCart);
        detailBorrowView.setVisible(true);
    }
}
