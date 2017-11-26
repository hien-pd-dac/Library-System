/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.helpers;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Ronaldo Hanh
 */
public class Session {
    private static Map<String, String> session = new HashMap();
    
    public static boolean exist(String key){
        return session.containsKey(key);
    }
    
    public static void add(String key, String value){
        if(!value.isEmpty()){
            session.put(key, value);
        }
    }
    
    public static String get(String key){
        return session.get(key);
    }
    
    public static String remove(String key){
        return session.remove(key);
    }
    
    public static void destroy(){
        session.clear();
    }
}
