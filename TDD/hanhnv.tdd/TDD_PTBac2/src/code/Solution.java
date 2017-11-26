/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.Arrays;

/**
 *
 * @author Ronaldo Hanh
 */
public class Solution implements giaiPTBac2 {

    @Override
    public long[] GPTBac2(long... operands) {
        long[] result = new long[3];
        if (operands.length < 3) {
            result[2] = 0;
            return result;
        }
        if (operands[0] == 0) {
            if (operands[1] == 0 && operands[2] != 0) {
                result[2] = 0;
                return result;
            } else if (operands[1] == 0 && operands[2] == 0) {
                result[2] = 1;
                return result;
            } else if (operands[1] != 0) {
                result[0] = -operands[2] / operands[1];
                return result;
            }
        } else {
            //Tham so truyen vao co 3 gia tri a,b,c
            long a = operands[0];
            long b = operands[1];
            long c = operands[2];
            long delta = b * b - 4 * a * c;
            if (delta == 0) {
                result[0] = -b / (2 * a);
                return result;
            } else if (delta > 0) {
                result[0] = (long) ((-b + Math.sqrt(delta)) / (2 * a));
                result[1] = (long) ((-b - Math.sqrt(delta)) / (2 * a));
                return result;
            } else if (delta < 0) {
                return result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution slt = new Solution();
        long[] exp = {-1, 0, 0};
        long[] result = slt.GPTBac2(1, 2, 1);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        //System.out.println(exp);
    }
}
