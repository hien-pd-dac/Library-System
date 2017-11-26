/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.models;

import java.util.Date;

/**
 *
 * @author hnv
 * Model card
 * @attribute userCode
 * @attribute expiredDate;
 * @attribute activationCode
 * @method getter and setter 
 */
public class CardModel {
    
    private String userCode;
    private Date expiredDate;
    private String activationCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
    
    
}
