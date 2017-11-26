/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ronaldo Hanh
 */
public class MainController {
    private static Map<Class, BaseController> controllers = new HashMap();
    
    private static void add(Class key, BaseController controller){
        controllers.put(key, controller);
    }
    
    private static <T extends BaseController> T getController(Class<T> target){
        if(controllers.get(target)!=null){
            return (T) controllers.get(target);
        }
        T controller = null;
        try{
            controller = target.newInstance();
            add(target, controller);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return controller;
    }
    
    public static void redirect_to(Class current, Class target){
        if(current!=null){
            getController(current).hideGUI();
        }
        getController(target).showGUI();
    }
    
    
}
