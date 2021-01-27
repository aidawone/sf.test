package com.zgr.Sort;

import java.util.Arrays;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 12:41 2020/1/17
 */
public class RedixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        Redix(arr);

    }

    public static void Redix(int[] arr) {
        //求出最大的数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //最大的几位数
        int maxLength = (max + "").length();

        //第一轮排序
        //个位排序
        //定义一个二维数组， 表示10个桶，每个桶就是一个一维数组
        //为了防止数据放入溢出，只能定义成arr.length，用空间换时间

        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中实际存放了多少的数据，定义一个一维数组记录各个桶放入的数据个数
        int[] bucketElementsCounts = new int[10];

        for (int l = 0, n = 1; l < maxLength; l++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                //个位数
                int digofElemts = arr[j] / n % 10;
                bucket[digofElemts][bucketElementsCounts[digofElemts]] = arr[j];
                bucketElementsCounts[digofElemts]++;
            }
            //取出数据
            int index = 0;
            for (int k = 0; k < bucketElementsCounts.length; k++) {
                if (bucketElementsCounts[k] != 0) {
                    for (int i = 0; i < bucketElementsCounts[k]; i++) {
                        arr[index] = bucket[k][i];
                        index++;
                    }
                }
                bucketElementsCounts[k] = 0;
            }
            System.out.println("第一轮：" + Arrays.toString(arr));

        }


    }
}
