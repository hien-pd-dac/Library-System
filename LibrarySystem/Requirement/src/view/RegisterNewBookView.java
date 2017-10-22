import modelodel.*;
import controller.*;
import javax.swing.*;
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
    
*/

class RegisterNewBookView extends JPanel implements ActionListener {
    /**
     * attribute RegisterNewBookForm là form đăng kí sách mới
    */
    private JPanel registerNewBookForm;

    /**
    * attribute tfBook nhập tên sách.
    */
    private JTextField tfBook;
    
     /**
    * attribute tfBook nhập tên tác giả.
    */
    
    private JTextField tfTacGia;
    /**
    * attribute cbbTheLoai là chọn thể loại của sách.
    */
    private JComboBox cbbTheLoai;
    /**
    * attribute cbbSoLuong là chọn số lượng sách.
    */
    private JComboBox cbbSoLuong;

    /**
    * attribute raMota là nhập đoạn mô tả về cuốn sách.
    */
    private JTextArea taMoTa;

    /**
    * attribute submitBtn là 1 button thực hiện submit đăng kí sách mới.
    */
    private JButton btnSubmit;
    /**
    * attribute registerNewBookController là 1 object của lớp RegisterNewBookController.
    */
    private RegisterNewBookController registerNewBookController  ;
     /**
    * attribute Book là 1 object của lớp Book.
    */
    private Book book;
    /**
     * method setRegisterNewBookController để khởi tạo đối tượng RegisterNewBookController của lớp RegisterNewBookController
     * @param RegisterNewBookController là đối tượng của lớp RegisterNewBookController
     */
    public void setRegisterNewBookController(RegisterNewBookController RegisterNewBookController) {
        this.RegisterNewBookController = RegisterNewBookController;
     /**
     * method setBook để khởi tạo đối tượng Book của lớp Book
     * @param book là đối tượng của lớp Book
     */
    }
    public void setBook(Book book) {
        this.book = book;
    }
    /**
    * contructor khởi tạo UI cho lớp
    */
    public RegisterNewBookView(){
        this.add(RegisterNewBookForm);
        this.add(tfBook);
        this.add(tfTacGia);
        this.add(cbbTheLoai);
        this.add(cbbSoLuong);
        this.add(taMoTa);      
        btnSubmit.addActionListener(this);
        this.setVisible(false);
    }
}

    /**
    * ghi đè phương thức trong interface ActionListener
    * thực hiện lắng nghe các sự kiện và thực thi hành động với interface RegisterSearchBookListener
    */
    @Override
    public void actionPerformed(ActionListener e){
               if(registerNewBookController.checkSubmit() == true) {
                    notify("Submit successfully!");
                } else {
                    notify("Submit false!");
                
            }
        }
    

