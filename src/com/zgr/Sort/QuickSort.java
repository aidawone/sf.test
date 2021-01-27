package com.zgr.Sort;

import java.util.Arrays;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 20:28 2020/1/15
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, -567, 70, 0};
        quickSort2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    public static void quickSort2(int[] arr, int left, int right) {
        //首先保存左右来两边的下标，以便递归
        int l = left;
        int r = right;
        //定义一个中轴
        int pivot = arr[(l + r) / 2];
        //临时变量来交换
        int temp;
        while (l < r) {
            //遍历左边不符合条件的交换
            while (arr[l] < pivot) {
                l += 1;
            }
            //遍历右边不符合条件的交换
            while (arr[r] > pivot) {
                r -= 1;
            }
            //当两边遍历完
            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //交换完了之后就判断是否值与中轴相等,相等则跳过
            //为什么跳过，如果left的值与其相等，被交换过来说明原先的l下标的值不能在左边是可以确定的，所以就位移跳过遍历它
            if (arr[l] == pivot) {
                r -= 1;
            }
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //判断l 与r,l==r说明都在中轴，这样在执行左递归或右递归时，就会携带中轴，所以前移或后移
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //递归
        if (left < r) {
            quickSort2(arr, left, r);
        }
        if (right > l) {
            quickSort2(arr, l, right);
        }
    }
}

