/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hephuongtrinh;

/**
 *
 * @author hpd
 */
public class NghiemToString {
    public static final String VO_SO_NGHIEM = "vo_so_nghiem";
    public static final String VO_NGHIEM = "vo_nghiem";
    public static final String nghiemToString (float x, float y) {
        if (x == -0f) x = 0f;
        if (y == -0f) y = 0f;
//        return String.format("(%f, %f)", x, y);
        return "(" + x +", " + y + ")";
    }
    
}
