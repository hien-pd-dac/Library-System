/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers.librarians;

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
public class CardIssueControllerTest {

    public CardIssueControllerTest() {
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

    @Test
    public void testIssueCard() {
        System.out.println("test issue Card");
        /**
         * Test case 1: Tên người dùng null
         */
        int[] operand1 = {0, 1, 1, 2020, 1};
        int expResult1 = 0;
        
        /**
         * Test case 2: Mã kích hoạt null
         */
        int[] operand2 = {1,1,1,2020,0};
        int expResult2 = 0;
        
        /**
         * Test case 3: Tên người dùng và mã kích hoạt null
         */
        int[] operand3 = {0,1,1,2020,0};
        int expResult3 = 0;

        /**
         * Test case 4: Mã kích hoạt phải có tối thiểu 6 kí tự 
         */
        int[] operand4 = {1,1,12,2018, 111};
        int expResult4 = 0;
        
        /**
         * Test case 5: Ngày hết hạn <ngày hiện tại
         */
        int[] operand5 = {1,1,12,2017,111111};
        int expResult5 = 0;
        
        /**
         * Test case 6: Input hợp lệ nhưng không tồn tại người vay
         */
        int[] operand6 = {1,7,12,2018,111111};
        int expResult6 = 0;
        
        /**
         * Test case 7: Input hợp lệ nhưng người vay đã có thẻ
         */
        int[] operand7 = {4,7,12,2018,111111};
        int expResult7 = 0;
        
        int[][] operands = {operand1, operand2, operand3, operand4, operand5, operand6, operand7};
        int[] expResults = {expResult1, expResult2, expResult3, expResult4, expResult5, expResult6, expResult7};
        for (int i = 0; i < operands.length; i++) {
            CardIssueController instance = new CardIssueController();
            int result = instance.testIssueCard(Integer.toString(operands[i][0]), operands[i][1], operands[i][2], operands[i][3], Integer.toString(operands[i][4]));
            assertEquals(expResults[i], result);
        }
    }

}
