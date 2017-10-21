/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.*;
import view.*;
/**
 * class CardIssueController có nhiệm vụ thực thi phần phát hành thẻ mới khi click  vào button Phát hành thẻ
 */
public class CardIssueController  {
    
    /**
     * hàm phát hành thẻ kiểm tra các điều kiện để có thể phát hành thẻ
     * @param card 
     */
    public void issueNewCard(Card card){
         if(checkExist(card) == false && checkValid(card) == true){
               System.out.println("Issue successful!");
               saveToDatabase(card);
           }
           else if(checkExist(card) == true){
               System.out.println("Card Exist!");
           }
           else if(checkValid(card) == false){
               System.out.println("Card is not valid");
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
