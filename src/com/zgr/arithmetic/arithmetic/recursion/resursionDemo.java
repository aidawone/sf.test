package com.zgr.arithmetic.arithmetic.recursion;

import sun.applet.Main;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 14:04 2020/1/14
 */
public class resursionDemo {
    public static void main(String[] args) {
        test(4);
    }

    public static void test(int n) {
        if (n > 2) {
            test(n - 1);

        }
        System.out.println("n=" + n);
    }
}
