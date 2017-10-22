/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Ronaldo Hanh
 */
public  class Card {
    /**
     * Định nghĩa các thuộc tính
     * Tất cả các thuộc tính đều là private 
     * @attribute cardID  có kiểu string bao gồm 8 kí tự
     * @attribute borrowerName kiểu string
     * @attribute role  kiểu string là Sinh viên hoặc không phải là sinh viên
     * @attribute expirationDate  kiểu Date
     * attribute activationCode kiểu string gồm 7 kí tự đi kèm với mã số thẻ vay
     */
    private String cardID;
    private String borrowerName;
    private String role;
    private Date expirationDate;
    private String activationCode;
    
    /**
     * Cài đặt constructor Card
     * @param cardID kiểu String
     * @param borrowerName kiểu String 
     * @param role kiểu String 
     * @param  expirationDate kiểu Date
     * @param activationCode kiểu String
     */
    public Card(String cardId, String borrowerName, String role, Date expirationDate, String activationCode){
        this.setCardID(cardID);
        this.setBorrowerName(borrowerName);
        this.setRole(role);
        this.setExpirationDate(expirationDate);
        this.setActivationCode(activationCode);
    }
    
    /**
     * Cài đặt phương thức getCardID 
     * Return a string
     */
    public String getCardID(){
        return this.cardID;
    }
    
     /**
     * Cài đặt phương thức getBorrowerName 
     * Return a string
     */
    public String getBorrowerName(){
        return this.borrowerName;
    }
    
    /**
     * Cài đặt phương thức getRole 
     * Return a string
     */
    public String getRole(){
        return this.role;
    }
    
    /**
     * Cài đặt phương thức getExpirationDate 
     * Return  Date form 
     */
    public Date getExpirationDate(){
        return this.expirationDate;
    }
    
     /**
     * Cài đặt phương thức getActivationCode
     * Return a string
     */
    public String getActivationCode(){
        return this.activationCode;
    }
    
     /**
     * Cài đặt phương thức setCardID
     * @param cardID kiểu string 
     */
    public void setCardID(String cardID){
        this.cardID = cardID;
    }
    
    /**
     * Cài đặt phương thức setBorrowerName
     * @param borrowerName kiểu string 
     */
    public void setBorrowerName(String borrowerName){
        this.borrowerName = borrowerName;
    }
    
    /**
     * Cài đặt phương thức setRole
     * @param role kiểu string 
     */
    public void setRole(String role){
        this.role = role;
    }
    
    /**
     * Cài đặt phương thức setExpirationDate
     * @param expirationDate kiểu Date
     */
    public void setExpirationDate(Date expirationDate){
        this.expirationDate = expirationDate;
    }
    
    /**
     * Cài đặt phương thức setActivationCode
     * @param activationCode kiểu String
     */
    public void setActivationCode(String activationCode){
        this.activationCode = activationCode;
    }
}
