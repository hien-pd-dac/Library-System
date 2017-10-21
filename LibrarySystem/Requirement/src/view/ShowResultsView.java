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

class ShowResults extends JPanel implements ActionListener{
    /**
     * attribute ibSach là tên sách tìm kiếm ra
     */
    private JLabel lbSach;
    /**
     * tạo UI Showresults 
     *
     */
    public ShowResultsView(){
        this.add(lbSach);
    }
    
}
/**
    * ghi đè phương thức trong interface ActionListener
    * bắt sự kiện khi click lbSach
    */
public void actionPerformed(ActionEvent e) {
    e.gerSource()== lbSach{
    ShowResultsController.Book(Book)
}
}