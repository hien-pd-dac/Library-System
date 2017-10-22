import model.*;
import view.*;

/**
 * class SearchBookController có nhiệm vụ thực thi tìm kiếm khi click Search
 */
public class SearchBookController {
    /**
     * Method khởi tạo SearchBookController
     */
    public SearchBookController(){
    }
    /**
     * tìm kiếm theo kí tự nhập vào
     * @param tfSearch là ký tự người dùng nhập
     * @return book nếu tìm thấy sách
     * báo lỗi nếu nhập sai kí tự
     */
    public Book SearchBook(String tfSearch){          
           if(checkValid(tfSearch) == true){
           Book book=null; 
           return book;}          
           }
           else if(checkValid(tfSearch) == false){
               System.out.println(" input is not valid");
           }      
    }
    
