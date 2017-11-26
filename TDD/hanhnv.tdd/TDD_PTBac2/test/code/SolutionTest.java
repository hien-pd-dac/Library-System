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
public class SolutionTest {
    
    public SolutionTest() {
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
     * Test of GPTBac2 method, of class Solution.
     */
    @Test
    public void testGPTBac2() {
        //System.out.println("GPTBac2");
        
        //Test 1 có nghiệm duy nhất
        long[] operands1 = {1,2,1};
        long[] expResult1 = new long[3];
        expResult1[0] = -1;
        
        //Test2 vô nghiệm  a!= 0
        long[] operands2 = {1,1,1};
        long[] expResult2 = new long[3];
        expResult2[2] = 0;
        
        //Test3 cos 2 nghiệm phân biệt
         long[] operands3 = {1,-3,2};
         long[] expResult3 = new long[3];
         expResult3[0] = 2; expResult3[1] = 1;
         
         //Test4 a=0, b,c !=0
         long[] operands4 = {0,1,1};
         long[] expResult4 = new long[3];
         expResult4[0] = -1;
         
         //Test4 nhap vao it hon 3 tham so => vo nghiem
         long[] operands5 = {1,1};
         long[] expResult5 = new long[3];
         expResult5[2] = 0;
         
         //Test5 a=b=c=0 => vo so nghiem
         long[] operands6 = {0,0,0};
         long[] expResult6 = new long[3];
         expResult6[2] = 1;
         
         //Test 6: a=b=0, c#0 => vo nghiem
         long[] operands7 = {0,0,1};
         long[] expResult7 = new long[3];
         expResult7[2] = 0;
         
        long[][] operands = {operands1, operands2, operands3, operands4, operands5, operands6, operands7};
        long[][] expResults = {expResult1, expResult2, expResult3, expResult4, expResult5, expResult6, expResult7};
        //System.out.println(operands[0][0]);
        for(int i = 0; i < operands.length; i++){
           Solution instance = new Solution();
           long[] result = instance.GPTBac2(operands[i]);
            //System.out.println(result[0]);
           assertArrayEquals(expResults[i], result);
        }
    }
}
