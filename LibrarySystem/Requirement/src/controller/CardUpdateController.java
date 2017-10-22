/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import model.*;
import view.*;

/**
 * class CardUpdateController có nhiệm vụ thực thi phần cập nhật thông tin thẻ khi click vào button cập nhật 
 * @author Ronaldo Hanh
 */
public class CardUpdateController {
    /**
     * @attribute searchCardView là 1 object của lớp FindCardView
     */
    private SearchCardView searchCardView;
    
    /**
     * Method khởi tạo của CardUpdateController
     */
    public CardUpdateController(){
       //do something 
    }
    /**
     * 
     * @param card 
     */
    public void updateCard(Card card){
         if(checkExist(card) == false && checkValid(card) == true){
               System.out.println("Update successful!");
               saveToDatabase(card);
           }
           else if(checkExist(card) == true){
               System.out.println("Card Exist!");
           }
           else if(checkValid(card) == false){
               System.out.println("Card's info is not valid");
           }       
    }
      /**
     * @param card là đối tượng của lớp Card
     * @return true nếu thông tin thẻ đầy đủ 
     * @return false nếu chưa nhập đủ thông tin thẻ 
     */
    private boolean checkValid(Card card){
        boolean check = false; 
        //code...
        if(check){
            return true;
        }
        else return false;
        
    }
    
    /**
     * 
     * @param card là đối tượng của lớp Card
     * @return true nếu card đã tồn tại trong cơ sở dữ liệu.
     * @return false nếu card chưa tồn tại trong cơ sở dữ liệu 
     */
    private boolean checkExist(Card card){
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
     * Method save card into database
     * @param card là đối tượng của lớp Card
     */
    private void saveToDatabase(Card card){
        //code...
    }
}
