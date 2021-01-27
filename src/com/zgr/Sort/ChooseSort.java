package com.zgr.Sort;

import java.util.Arrays;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 14:47 2020/1/15
 */
public class ChooseSort {
    public static void main(String[] args) {
        int[] map = {-1, 20, -5, -9, -10};
        choose(map);
        System.out.println(Arrays.toString(map));
    }

    public static void choose(int[] choosed) {
        //选择排序
        //就冒泡排序相反
        for (int i = 0; i < choosed.length - 1; i++) {
            //假定最小值
            int min = choosed[i];
            //假定最小值的下标
            int minIndex = i;
            for (int j = i + 1; j < choosed.length; j++) {
                if (min > choosed[j]) {
                    min = choosed[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                choosed[minIndex] = choosed[i];
                choosed[i] = min;
            }
        }
    }
}
