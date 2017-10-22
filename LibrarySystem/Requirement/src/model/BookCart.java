package model;

import model.Book;
/**
 *
 * @author Phạm Đức Hiển created at 21/10/2017
 *  class BookCart chứa thông tin chi tiết về giỏ hàng.
 */
public class BookCart {
   /**
    * @attribute cardID save cardID of borrower
    */
   private String cardID;

   /**
    * @attribute number save number of books in bookCart
    */
   private int number;

   /**
    * @attribute books save infomation of books in bookCart
    */
   private Book[] books;

   /**
    * @attribute borrowedAt save the day of borrowing.
    */
   private Date borrowedAt;

   /**
    * @attribute returnAt save the day of returning book.
    */
   private Date returnAt;

   /**
    * @attribute cardID save the status of bookCart
    * 0 if pending.
    * 1 if borrowing.
    * 2 if returned.
    */
   private int status;

   /**
    * constructor
    */
   public BookCart() {

   }

    public string getCardID() {
        return cardID;
    }

    public void setCardID(string cardID) {
        this.cardID = cardID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Date getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(Date borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public Date getReturnAt() {
        return returnAt;
    }

    public void setReturnAt(Date returnAt) {
        this.returnAt = returnAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

   /**
    * static method getBookCart
    * @return trả về mảng các bookCart từ các từ khóa tìm kiếm
    */
    public static BookCart[] getBookCart(Date borrowDate, String cardID, String userName) {
        return BookCart.filter("borrowDate"=borrowDate, "cardID"=cardID, "userName"=userName).all()
    }

    /**
     *
     * @param book quyển sách muốn add vào giỏ
     * @return true nếu thành công
     */
    public boolean addBookToCart(Book book) {
        if(this.number >= 5 || book.getStatus() == UNAVALIBALE) {
            return false;
        } else {
            this.books.push(book);
            this.setNumber(this.number+1);
            return true;
        }
    }

    public boolean hasUnReturnBook(){
        for (i=0; i < number; i++) {
            if(books[i].getStatus() == NOT_RETURNED){
                return true;
            }
        }
        return false;
    }
}
