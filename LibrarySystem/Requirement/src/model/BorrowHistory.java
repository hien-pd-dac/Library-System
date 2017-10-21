package model;

import model.Card;
import model.BookCart;
/**
 *
 * @author Pham Duc Hien
 * class BorrowHistory : lịch sử đăng kí đặt sách của user.
 */
public class BorrowHistory {
    /**
     * @attribute card thông tin thẻ.
     */
    private Card card;

    /**
     * @attribute bookCart : thông tin về các giỏ sách đã đăng kí.
     */
    private BookCart[] bookCart;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    /**
     * constructor
     * @param card
     */
    public BorrowHistory(Card card) {
        this.setCard(card);
    }

    public boolean hasUnreturnBook() {
        int number = bookCart.count();
        if(BookCart[number-1].hasUnReturnBook()){
            return true;
        } else {
            return false;
        }
    }
}
