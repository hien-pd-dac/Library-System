package controller;

import javax.swing.*;
import java.awt.event.ActionListener;
import model.Card;
import model.BookCart;
import model.BorrowHistory;
/**
    * Created by Pham Duc Hien on 21/10/2017
    * class RegisterBorrowBookController control view action
*/
public class RegisterBorrowBookController {
    /**
    * attribute card : info card.
    */
    private Card card;

    /**
    * attribute borrowHistory chứa danh sách các giỏ sách đã đăng kí mượn.
    */
    private BorrowHistory borrowHistory;

    /**
    * attribute bookCart chứa danh sách các cuốn sách trong giỏ đăng kí.
    */
    private BookCart bookCart;

    /**
    * contructor
    * @param cardID info about user's card
    */
    public RegisterBorrowBookController(Card card) {
        this.card = card;
        this.borrowHistory = new BorrowHistory(card);
        this.bookCart = new BookCart();
    }

    /**
    * go to SearchBookView
    */
    public void goToSearchBookView() {
        SearchBookView searchBookView = new SearchBookView();
        display(searchBookView);
    }

    /**
    * @return true if card is expired
    */
    private boolean isExpiredCard() {
        if(card.isExpiredCard()) {
            return true;
        }
    }

    /**
    * @return true if has unreturn book.
    */
    private boolean hasUnreturnBook() {
        if(borrowHistory.hasUnreturnBook()) {
            return true;
        }
    }

    /**
    * @return true if the number of book in cart is over 5.
    */
    private boolean isOverFive() {
        if(bookCart.count() > 5) {
            return true;
        }
    }

    /**
    * @return true if registration is successful
    */
    public boolean submit() {
        if(isExpiredCard() || hasUnreturnBook() || isOverFive()) {
            return false;
        } else {
            BorrowHistory.add(bookCart);
            return true;
        }
    }
}
