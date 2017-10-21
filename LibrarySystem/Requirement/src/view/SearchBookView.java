import Model.*;
import Controller.*;
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
 *
 * @author Việt Hưng
 */
class SearchbookView extends JPanel implements ActionListener {
    /**

    */
    private SearchBookController SearchBookController;

    /**
    * nhập ký tự từ bàn phím
    */
    private JTextField tfSearch;
    /**
     * chọn thể loại cần tìm
     */
    private JRadioButton rbQuanTam;

    /**
    * attribute submitS là 1 button thực hiện input keywork vào hệ thống.
    */
    private JButton btnSearch;
    


    /**
    * contructor khởi tạo UI cho lớp
    */
    public RegisterBorrowBookView(){
        this.add(tfSearch );
        this.add(rbQuanTam);
        btnSearch.addActionListener(this);
        setVisible(false);
     
    }
    
    /**
    * ghi đè phương thức trong interface ActionListener
    * bắt sự kiện khi click btnSearch
        */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSearch){
            Add a comment to this line
            Book = SearchBookController.SearchBook(tfSearch);
            if(book!=null){
                this.setVisible(false);
                SearchBookView.setVisible(true);
            }
           else{
                System.out.println("Find not found");
                //do something...
            }
            
        }
    }  
}
    

