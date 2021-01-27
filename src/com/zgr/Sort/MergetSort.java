package com.zgr.Sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 22:33 2020/1/15
 */
public class MergetSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        merget(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    //分+和方法
    public static void merget(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //左递归
            merget(arr, left, mid, temp);
            //右递归
            merget(arr, mid + 1, right, temp);
            //合并
            MergetSort(arr, left, mid, right, temp);

        }

    }

    /**
     * @param arr
     * @param left  左边有序序列的起始索引
     * @param mid   中间索引
     * @param right 右边有序序列的起始索引
     * @param temp  中装数组
     */
    public static void MergetSort(int[] arr, int left, int mid, int right, int[] temp) {
        //左边的开始索引
        int i = left;
        //右边的开始索引
        int j = mid + 1;
        int index = 0; //temp数组的索引
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[index] = arr[i];
                index += 1;
                i += 1;
            } else {
                temp[index] = arr[j];
                index += 1;
                j += 1;
            }
        }
        //把有剩余的加入到temp中
        while (i <= mid) {
            temp[index] = arr[i];
            index += 1;
            i += 1;
        }
        while (j <= right) {
            temp[index] = arr[j];
            index += 1;
            j += 1;
        }
        //每次合并的都不一样

        index = 0;
        int tempLeft = left;
        System.out.println("tempLeft=" + tempLeft + "right" + right);
        while (tempLeft <= right) {
            arr[tempLeft] = temp[index];
            //拷贝到arr数组中
            index += 1;
            tempLeft += 1;
        }


    }
}
