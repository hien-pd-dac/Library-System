/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.librarians;

import com.library.controllers.BaseController;
import com.library.controllers.MainController;
import com.library.helpers.Session;
import com.library.models.CardModel;
import com.library.views.librarians.CardSearchView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Controller quản lí chức năng tìm kiếm thẻ
 *
 * @author Ronaldo Hanh
 */
public class CardSearchController implements BaseController {

    private CardSearchView view;
    private CardModel card;

    /**
     * Constructor
     */
    public CardSearchController() {
        card = new CardModel();
        view = new CardSearchView();
        view.addButtonSearchListener(new CardSearchListener());
        view.addButtonShowAllListener(new CardShowAllListener());
        view.addButtonExitListener(new ExitListener());
        view.addTableClickedListener(new TableResultClickedListener());
    }

    @Override
    public void hideGUI() {
        clearTable(view.getTableResult());
        view.setVisible(false);
        //view.getPane().setVisible(false);
        
    }

    @Override
    public void showGUI() {
        view.setVisible(true);
        view.getPane().setVisible(true);
        

    }

    /**
     * Hàm clear bảng sau khi thực hiện tìm kiếm
     * @param table
     */
    public void clearTable(JTable table) {
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                table.setValueAt("", i, j);
            }
        }
    }

    /**
     * Lớp lắng nghe sự kiện khi click vào button tìm kiếm thẻ
     */
    class CardSearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Session.add("cardID", view.getCardID());
            Session.add("userName", view.getUserName());
            Session.add("fullName", view.getFullName());
            //System.out.println("cardID: " +  Session.get("cardID"));
            if (validateInputData() == true) {
                Vector columnName = setColumnName();
                if (Session.get("cardID") != null && Session.get("userName") == null && Session.get("fullName") == null) {
                    Vector data = card.searchFollowCardID();
                    if (data != null) {
                        view.getPane().setVisible(true);
                        view.getTableResult().setModel(new DefaultTableModel(data, columnName));
                        view.getTableResult().setEnabled(false);
                    }
                } else if (Session.get("cardID") == null && Session.get("userName") != null && Session.get("fullName") == null) {
                    Vector data = card.searchFollowUserName();
                    if (data != null) {
                        view.getPane().setVisible(true);
                        view.getTableResult().setModel(new DefaultTableModel(data, columnName));
                        view.getTableResult().setEnabled(false);
                    }
                } else if (Session.get("cardID") == null && Session.get("userName") == null && Session.get("fullName") != null) {
                    Vector data = card.searchFollowFullName();
                    if (data != null) {
                        view.getPane().setVisible(true);
                        view.getTableResult().setModel(new DefaultTableModel(data, columnName));
                        view.getTableResult().setEnabled(false);
                    }
                } else if (Session.get("cardID") != null && Session.get("userName") != null && Session.get("fullName") == null) {
                    Vector data = card.searchFollowCardIDUserName();
                    if (data != null) {
                        view.getPane().setVisible(true);
                        view.getTableResult().setModel(new DefaultTableModel(data, columnName));
                        view.getTableResult().setEnabled(false);
                    }
                } else if (Session.get("cardID") != null && Session.get("userName") == null && Session.get("fullName") != null) {
                    Vector data = card.searchFollowCardIDFullName();
                    if (data != null) {
                        view.getPane().setVisible(true);
                        view.getTableResult().setModel(new DefaultTableModel(data, columnName));
                        view.getTableResult().setEnabled(false);
                    }
                } else if (Session.get("cardID") == null && Session.get("userName") != null && Session.get("fullName") != null) {
                    Vector data = card.searchFollowUserNameFullName();
                    if (data != null) {
                        view.getPane().setVisible(true);
                        view.getTableResult().setModel(new DefaultTableModel(data, columnName));
                        view.getTableResult().setEnabled(false);
                    }
                } else if (Session.get("cardID") != null && Session.get("userName") != null && Session.get("fullName") != null) {
                    Vector data = card.searchFollowAll();
                    if (data != null) {
                        view.getPane().setVisible(true);
                        view.getTableResult().setModel(new DefaultTableModel(data, columnName));
                        view.getTableResult().setEnabled(false);
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Bạn phải nhập ít nhất 1 trong 3 trường dữ liệu!");
            }
            Session.destroy();
        }

        /**
         * function validate dữ liệu nhập vào để tìm kiếm
         *
         * @return false nếu không nhập vào bất kì trường nào để tìm kiếm
         * @return true nếu nhập ít nhất 1 trong 3 trường dữ liệu để tìm kiếm
         */
        private boolean validateInputData() {
            if (Session.get("cardID") == null && Session.get("userName") == null && Session.get("fullName") == null) {
                return false;
            } else {
                return true;
            }
        }

        /**
         * Hàm set tên các cột cho bảng dữ liệu tìm kiếm
         *
         * @return Vector columnName
         */
        private Vector setColumnName() {
            Vector columnName = new Vector();
            columnName.add("Mã số thẻ");
            columnName.add("Ngày hết hạn");
            columnName.add("Tên đăng nhập");
            columnName.add("Tên người mượn");
            return columnName;
        }

    }

    /**
     * Lớp lắng nghe sự kiện khi click button hiển thị tất cả thẻ
     */
    class CardShowAllListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Vector data = card.showAll();
            Vector columnName = setColumnName();
            if (data != null) {
                view.getPane().setVisible(true);
                view.getTableResult().setModel(new DefaultTableModel(data, columnName));
                view.getTableResult().setEnabled(false);
            }

        }

        /**
         * Hàm set tên các cột cho bảng dữ liệu tìm kiếm
         *
         * @return Vector columnName
         */
        private Vector setColumnName() {
            Vector columnName = new Vector();
            columnName.add("Mã số thẻ");
            columnName.add("Ngày hết hạn");
            columnName.add("Tên đăng nhập");
            columnName.add("Tên người mượn");
            return columnName;
        }

    }

    /**
     * Lớp lắng nghe sự kiện khi click vào button quay lại
     */
    class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.redirect_to(CardSearchController.class, CardManageController.class);
        }

    }

    /**
     * Lớp quản lí sự kiện khi click vào 1 dòng trong bảng kết quả trả về
     */
    class TableResultClickedListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                int row = view.getTableResult().rowAtPoint(e.getPoint());
                String id = view.getTableResult().getValueAt(row, 0).toString();
                System.out.println("ID clicked: " + id);
                Session.add("IDClicked", id);
                MainController.redirect_to(CardSearchController.class, CardDetailController.class);
                //System.out.println("ID clicked: " + id);

            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

}
