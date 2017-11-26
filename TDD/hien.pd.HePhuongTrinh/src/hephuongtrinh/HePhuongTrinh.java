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
public class HePhuongTrinh {

    /**
     *
     * @param a1
     * @param b1
     * @param c1
     * @param a2
     * @param b2
     * @param c2
     * @return
     */
    public static String giaiHePhuongTrinh (float a1, float b1, float c1,
                                     float a2, float b2, float c2) {
        
        float d = a1*b2 - a2*b1;
        float dX = b2*c1 - b1*c2;
        float dY = a1*c2 - a2*c1;
        float x, y;
        
        if (d == 0) {
            if (dX != 0 || dY != 0) {
                return NghiemToString.VO_NGHIEM;
            } else {
                return NghiemToString.VO_SO_NGHIEM;
            }
        } else {
            x = dX/d;
            y = dY/d;
            return NghiemToString.nghiemToString(x, y);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String kq = HePhuongTrinh.giaiHePhuongTrinh(1, 2, 3, 1, 2, 3);
        System.out.println(kq);
        
    }
    
}
