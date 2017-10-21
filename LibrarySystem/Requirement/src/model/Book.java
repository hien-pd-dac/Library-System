
/**
 * Định nghĩa các thuộc tính
 * @attribute BookID có kiểu string gồm 8 ký tự
 * @attribute NameBook có kiểu string gồm 30 ký tự
 * @attribute TheLoai có kiểu string gồm 30 ký tự
 * @attribute SoLuong có kiểu int gồm 2 kí tự
 * @attribute mota có kiểu string gồm 5000 kí tự
 */
public class Book {
    private String bookID;
    private String nameBook;
    private String tacGia;
    private String theLoai;
    private int soluong;
    private String mota;
    

public Book(String bookID,String tacGia, String nameBook, String theLoai, int soluog, String mota ){
    this.setBookID(bookID);
    this.setNameBook(nameBook);
    this.setTacGia(tacGia);
    this.setTheLoai(theLoai);
    this.setSoLuong(soluong);
    this.setMoTa(mota);
}
}
    /**
     * Cài đặt phương thức getBookID 
     * Return a string
     */
    public String getBookID(){
        return this.bookID;
    }
    /**
     * Cài đặt phương thức getNameBookID 
     * Return a string
     */
    public String getNameBook(){
        return this.nameBookID;
    /**
     * Cài đặt phương thức getTacGia 
     * Return a string
     */
    public String getTacGia(){
        return this.tacGia;
    }
    }  /**
     * Cài đặt phương thức getTheLoai
     * Return a string
     */
    public String getTheLoai(){
        return this.theloai;
    }  /**
     * Cài đặt phương thức getSoLuong 
     * Return a int
     */
    public int getSoLuong(){
        return this.soluong;
    }  /**
     * Cài đặt phương thức getmota
     * Return a string
     */
    public String getMota(){
        return this.mota;
    }
/**
     * Cài đặt phương thức setBookID 
     * @param bookID kiểu string
     */
    public String getBookID(string bookID){
        this.BookID=bookID;
}
    /**
     * Cài đặt phương thức setBookID 
     * @param bookID kiểu string
     */
    public String setBookID(string bookID){
        this.BookID=bookID;
}
    /**
     * Cài đặt phương thức setNameBook
     * @param nameBook kiểu string
     */
    public String getNameBook(string nameBook){
        this.NameBook=nameBook;
}
    /**
     * Cài đặt phương thức setTacGia 
     * @param tacGia kiểu string
     */
    public String getTacGIa(string tacGia){
        this.TacGia=tacGia;
}
    /**
     * Cài đặt phương thức setTheLoai 
     * @param theLoai kiểu string
     */
    public String getTheLoai(string theLoai){
        this.TheLoai=theLoai;
}
    /**
     * Cài đặt phương thức setSoLuong
     * @param soluong kiểu int
     */
    public String getSoLuong(int soluong){
        this.SoLuong=soluong;
}
/**
     * Cài đặt phương thức setMoTa
     * @param mota kiểu string
     */
    public String getMoTa(string mota){
        this.Mota=mota;
}