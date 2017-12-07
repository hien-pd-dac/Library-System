/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.utils;

/**
 *
 * @author hpd
 */
public class Utils {
    
    /**
     *
     */
    
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 600;
    public static final int HEADER_HEIGHT = 70;
    public static final int CONTENT_HEIGHT = 400;
    public static final int CONTENT_WIDTH = 700;
    
    public static final String LOGIN_BTN = "login_btn";
    public static final String BACK_BTN = "backBtn";
    public static final String SEARCH_BTN = "searchBtn";
    public static final String ADD_TO_CART_BTN = "addToCartBtn";
    public static final String SEARCH_BOOK_BTN = "searchBookBtn";
    public static final String BOOK_CART_BTN = "bookCartBtn";
    public static final String BORROWED_HISTORY_BTN = "borrowedHistoryBtn";
    public static final String ACCOUNT_BTN = "accountBtn";
    public static final String LOGOUT_BTN = "logoutBtn";
    public static final String REMOVE_BTN = "removeBtn";
    public static final String SUBMIT_BTN = "submitBtn";
    public static final String ADD_MORE_BTN = "addMoreBtn";

//    public static void debug(Class clz,String debug){
//        if (true){
//            debug = debug == null ? "Null debug string!" : debug;
//            String name = clz.getCanonicalName()==null?"Debug": clz.getCanonicalName();
//            System.out.println(name+":"+debug);
//        }
//    }
    public static void debug(String msg) {
        System.out.println(msg);
    }
    
}
