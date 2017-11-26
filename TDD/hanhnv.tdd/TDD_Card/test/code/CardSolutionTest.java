/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

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
public class CardSolutionTest {

    public CardSolutionTest() {
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
     * Test of issueCardToDB method, of class CardSolution.
     */
    @Test
    public void testIssueCardToDB() {
        System.out.println("Test issueCardToDB");

        //Test 1: Thong tin nhap vao hop le
        int[] operand1 = {12345, 25, 11, 2018, 1111};
        int[] expResult1 = {1, 1, 1};

        //Test 2: Tên người dùng = null
        int[] operand2 = {0,26,11,2018,11};
        int[] expResult2 = {0, 1, 1};

        //Test 3: Activate code = null;
        int[] operand3 = {111,26,11,2018,0};
        int[] expResult3 = {1, 1, 0};
        //expResult3[0] = 1;expResult3[1] = 1; expResult3[2] = 0;

        //Test 4: Tên người dùng null và activate code null
        int[] operand4 = {0,2,3,2018,0};
        int[] expResult4 = {0, 1, 0};

        //Test 5: Năm hết hạn < năm hiện tại 
        int[] operand5 = {11, 2, 3, 2016, 11};
        int[] expResult5 = {1, 0, 1};

        //Test 6: Tháng hết hạn < Tháng hiện tại 
        int[] operand6 = {11, 2, 10, 2017, 11};
        int[] expResult6 = {1, 0, 1};

        //Test 7: Ngay hết hạn < ngày hiện tại
        int[] operand7 = {11, 25, 11, 2017, 11};
        int[] expResult7 = {1, 0, 1};

        //Test 8: Không nhập tên và ngày không hợp lệ 
        int[] operand8 = {0, 24, 11, 2017, 11};
        int[] expResult8 = {0, 0, 1};

        //Test 9: Ngày không hợp lệ và không nhập code
        int[] operand9 = {11, 24, 11, 2017, 0};
        int[] expResult9 = {1, 0, 0};

        //Test 10: cả 3 trường nhập không hợp lệ 
        int[] operand10 = {0, 24, 11, 2017, 0};
        int[] expResult10 = {0, 0, 0};

        int[][] expResults = {expResult1, expResult2, expResult3, expResult4, expResult5, expResult6, expResult7, expResult8, expResult9, expResult10};
        int[][] operands = {operand1, operand2, operand3, operand4, operand5, operand6, operand7, operand8, operand9, operand10};

        for (int i = 0; i < operands.length; i++) {
            CardSolution instance = new CardSolution(Integer.toString(operands[i][0]), operands[i][1], operands[i][2], operands[i][3], Integer.toString(operands[i][4]));
            int[] result = instance.issueCardToDB();
            assertArrayEquals(expResults[i], result);
        }
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testSearchCard(){
        System.out.println("Test Search Card");
        //Test 1: Nhập mã thẻ hợp lệ -> has result 
        int[] operand1 = {1,0};
        int expResult1 = 1;
        
        
        //Test 2: Mã thẻ không tồn tại -> no result 
        int[] operand2 = {6,0};
        int expResult2 = 0;
        
        //Test 3: Nhập username -> has result 
        int[] operand3 = {0, 111};
        int expResult3 = 1;
        
        //Test 4: Nhập username -> no result
        int[] operand4 = {0,112};
        int expResult4 = 0;
        
        
        //Test 5: Nhập cardID và username -> has result
        int[] operand5 = {2, 111};
        int expResult5 = 1;
        
        //Test 6: Nhập cardID và username -> no result 
        int[] operand6 = {1, 111};
        int expResult6 = 0;
        
        //Test 7: Không nhập cardID và username
        int[] operand7 = {0,0};
        int expResult7 = 2;
        
        int[][] operands = {operand1, operand2, operand3,operand4, operand5, operand6, operand7};
        int[] expResults = {expResult1, expResult2, expResult3, expResult4, expResult5, expResult6, expResult7};
        
        
        for(int i = 0; i < operands.length; i++){
            CardSolution solution = new CardSolution("hanh", 1, 1, 1, "hanh");
            int result = solution.searchCard(operands[i]);
            assertEquals(expResults[i], result);
        }
    }
}
