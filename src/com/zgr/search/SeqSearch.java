package com.zgr.search;

/**
 * 线性查找
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 14:13 2020/1/17
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int i = seqSearch(arr, 11);
        if (i == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("下标是" + i);
        }
    }

    public static int seqSearch(int[] arr, int value) {
        arr[0] = value;
        int i = arr.length - 1;
        while (arr[i] != value) {
            i--;
        }
        return i;
    }
}
