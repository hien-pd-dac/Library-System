/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.librarians;

import javax.swing.JTable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ronaldo Hanh
 */
public class CardSearchControllerTest {
    
    public CardSearchControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of testSearchCard method, of class CardSearchController.
     */
    @Test
    public void testTestSearchCard() {
        System.out.println("testSearchCard");
        
        /**
         * Test case 1: Cả 3 trường đều null
         */
        String[] operand1 = {"", "", ""};
        int expResult1 = 0;
        
        /**
         * Test case 2: Mã số thẻ != null, không tồn tại thẻ trong csdl
         */
        String[] operand2 = {"11", "", ""};
        int expResult2 = 0;
        
        /**
         * Test case 3: Mã số thẻ != null tồn tại thẻ trong csdl
         */
        String[] operand3 = {"1", "", ""};
        int expResult3 = 1;
    
         /**
         * Test case 4: Tên đăng nhập != null không tồn tại thẻ trong csdl
         */
        String[] operand4 = {"", "1234567", ""};
        int expResult4 = 0;
        
         /**
         * Test case 5: Tên đăng nhập != null tồn tại thẻ trong csdl
         */
        String[] operand5 = {"", "12345678", ""};
        int expResult5 = 1;
        
        /**
         * Test case 6: Tên người dùng != null không tồn tại thẻ trong csdl
         */
        String[] operand6 = {"", "", "nguyenducnhan"};
        int expResult6 = 0;
    
         /**
         * Test case 7: Tên đăng nhập != null tồn tại thẻ trong csdl
         */
        String[] operand7 = {"", "", "nguyen duc hien"};
        int expResult7 = 1;
        
         /**
         * Test case 8: Mã thẻ và tên đăng nhập != null không tồn tại thẻ trong csdl
         */
        String[] operand8 = {"1", "1234567", ""};
        int expResult8 = 0;
        
         /**
         * Test case 9: Mã thẻ và tên đăng nhập != null tồn tại thẻ trong csdl
         */
        String[] operand9 = {"1", "12345678", ""};
        int expResult9 = 1;
        
         /**
         * Test case 10: Mã thẻ và tên nguoi dung != null không tồn tại thẻ trong csdl
         */
        String[] operand10 = {"1", "", "nggg"};
        int expResult10 = 0;
        
        /**
         * Test case 11: Mã thẻ và tên nguoi dung != null tồn tại thẻ trong csdl
         */
        String[] operand11 = {"1", "", "nguyen duc hien"};
        int expResult11 = 1;
        
        /**
         * Test case 11: Tên đăng nhập và tên nguoi dung != null không tồn tại thẻ trong csdl
         */
        String[] operand12 = {"", "123456", "nguyen duc hien"};
        int expResult12 = 0;
        
        /**
         * Test case 13: Tên đăng nhập và tên nguoi dung != null tồn tại thẻ trong csdl
         */
        String[] operand13 = {"", "12345678", "nguyen duc hien"};
        int expResult13 = 1;
        
        /**
         * Test case 14: Cả 3 trường != null, không tồn tại trong csdl
         */
        String[] operand14 = {"1", "aa", "nguyen duc hien"};
        int expResult14 = 0;
        
        /**
         * Test case 15: Cả 3 trường != null, tồn tại trong csdl
         */
        String[] operand15 = {"1", "12345678", "nguyen duc hien"};
        int expResult15 = 1;
        
        String[][] operands = {operand1, operand2, operand3, operand4, operand5, operand6, operand7, operand8, operand9, operand10, operand11, operand12, operand13, operand14, operand15};
        int[] expResults = {expResult1, expResult2, expResult3, expResult4, expResult5, expResult6, expResult7, expResult8, expResult9, expResult10, expResult11, expResult12, expResult13, expResult14, expResult15};
        for (int i = 0; i < operands.length   ; i++) {
            CardSearchController instance = new CardSearchController();
            int result = instance.testSearchCard((operands[i][0]), operands[i][1], operands[i][2]);
            assertEquals(expResults[i], result);
        }
    }
    
}
