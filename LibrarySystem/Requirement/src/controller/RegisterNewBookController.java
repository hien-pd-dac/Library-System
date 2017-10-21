import model.*;
import view.*;
public class RegisterNewBookController  {
    
    /**
     * hàm phát hành thẻ kiểm tra các điều kiện để có thể phát hành thẻ
     * @param book là lớp của Book
     */
    public void RegisterNewBook(Book book){
        if(checkExist(book) == false && checkValid(book) == true){
               System.out.println("RegisterNewBook successful!");
               saveToDatabase(book);
           }
           else if(checkExist(book) == true){
               System.out.println("book Exist!");
           }
           else if(checkValid(book) == false){
               System.out.println(" input is not valid");
           }       
    }
    /**
     * @param book là đối tượng của lớp Book
     * @return true nếu thông tin sách đầy đủ 
     * @return false nếu chưa nhập đủ thông tin thẻ 
     */
    private boolean checkValid(Book book){
        boolean check = false; 
        if(check){
            return true;
        }
        else return false;
        
    }
   
    /**
     * 
     * @param book là đối tượng của lớp Book
     * @return true nếu card đã tồn tại trong cơ sở dữ liệu.
     * @return false nếu card chưa tồn tại trong cơ sở dữ liệu 
     */
    private boolean checkExist(Book book){
        boolean check = false;
        //code...
        if(check){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Method lưu sách vào database
     * @param card là đối tượng của lớp Card
     */
    private void saveToDatabase(Book book){
    }
}