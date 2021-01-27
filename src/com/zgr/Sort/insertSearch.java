package com.zgr.Sort;

import java.awt.*;

/**
 * 插值查找
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 9:40 2020/1/19
 */
public class insertSearch {
    public static void main(String[] args) {
        int arr[] = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        int arr2[] = {1, 2, 3, 4, 5, 6, 7, 7, 7, 7, 8};
        int i = insertSearch(arr, 0, 99, 99);
        System.out.println(i);
    }

    public static int insertSearch(int[] arr, int left, int right, int findValue) {
        //findValue < arr[left] || findValue>arr[right]
        //由于findValue是参与运算的，需要条件，不然会发生数组越界
        System.out.println("hello~");
        if (left > right || findValue < arr[left] || findValue > arr[right]) {
            return -1;

        }
        //中轴
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findValue > midVal) {
            return insertSearch(arr, mid + 1, right, findValue);
        } else if (findValue < midVal) {
            return insertSearch(arr, left, mid - 1, findValue);

        } else {
            return mid;
        }


    }
}
