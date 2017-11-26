/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hephuongtrinh;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hpd
 */
public class HePhuongTrinhTest {
    
    public HePhuongTrinhTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of giaiHePhuongTrinh method, of class HePhuongTrinh.
     */
    @Test
    public void testGiaiHePhuongTrinh() {
        String expResult;
        String result;
        
        // D = 0, Dx = 0, Dy = 0 -> vo so nghiem
        float a1 = 1F;
        float b1 = 1F;
        float c1 = 1F;
        float a2 = 1F;
        float b2 = 1F;
        float c2 = 1F;
        expResult = NghiemToString.VO_SO_NGHIEM;
        result = HePhuongTrinh.giaiHePhuongTrinh(a1, b1, c1, a2, b2, c2);
        assertEquals(expResult, result);
        
        // D = 0, Dx != 0, Dy != 0 -> vo nghiem
        // ( neu D = 0 && Dx != 0 suy ra Dy != 0)
        a1 = 1F;
        b1 = 1F;
        c1 = 2F;
        a2 = 1F;
        b2 = 1F;
        c2 = 3F;
        expResult = NghiemToString.VO_NGHIEM;
        result = HePhuongTrinh.giaiHePhuongTrinh(a1, b1, c1, a2, b2, c2);
        assertEquals(expResult, result);
        
        // D != 0, Dx = 0, Dy != 0
        a1 = 1F;
        b1 = 1F;
        c1 = 1F;
        a2 = 2F;
        b2 = 1F;
        c2 = 1F;
        expResult = NghiemToString.nghiemToString(0, 1);
        result = HePhuongTrinh.giaiHePhuongTrinh(a1, b1, c1, a2, b2, c2);
        assertEquals(expResult, result);
        
        // D != 0, Dx != 0, Dy = 0 
        a1 = 1F;
        b1 = 2F;
        c1 = 1F;
        a2 = 1F;
        b2 = 3F;
        c2 = 1F;
        expResult = NghiemToString.nghiemToString(1, 0);
        result = HePhuongTrinh.giaiHePhuongTrinh(a1, b1, c1, a2, b2, c2);
        assertEquals(expResult, result);
        
        // D != 0, Dx != 0, Dy != 0 
        a1 = 1F;
        b1 = 2F;
        c1 = 1F;
        a2 = 1F;
        b2 = 3F;
        c2 = 4F;
        expResult = NghiemToString.nghiemToString(-5, 3);
        result = HePhuongTrinh.giaiHePhuongTrinh(a1, b1, c1, a2, b2, c2);
        assertEquals(expResult, result);
    }

    /**
     * Test of main method, of class HePhuongTrinh.
     */
    
}
