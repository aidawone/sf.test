package com.zgr.Sort;

import java.util.Arrays;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 9:57 2020/1/19
 */
public class fibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1, 89, 10, 1000, 1234};
        int i = feiboSearch(arr, 1234);
        System.out.println(i);

    }

    //获取斐波那契数组
    public static int[] feibo() {
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < 20; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * @param arr 数组
     * @param key 查找的数
     * @return 如果没有则返回-1
     */
    public static int feiboSearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;
        int[] f = feibo();//斐波那契数组
        //获取斐波那契分割数值，即mid
        //因为可能要查找的数组的长度小于斐波那契，需要判断
        //f[k]-1是斐波那契规律，是顺序数组的长度
        while (right >= f[k] - 1) {
            k++;
        }
        //数组长度不够时，增长到n+1;不够的部分用0填充
        int[] temp = Arrays.copyOf(arr, f[k] - 1);

        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }
        //接下来就是mid
        while (left <= right) {
            mid = left + f[k - 1] - 1;
            //判断方向
            if (key < temp[mid]) {
                right = mid - 1;
                //为什么是k--;
                // 1.全部元素=前边元素+后边元素
                // f[k]= f[k-1]+f[k-2];
                // 2.因为前面有f[k-1]个元素 所以可以拆分成f[k-1]=f[k-2]+f[k-3];
                // 即在f[k-1]的前面继续查找k--
                //下次循环 mid = f[k-1-1]-1;
                k--;
            } else if (key > temp[mid]) {
                left = mid + 1;
                //为什么是k-=2;
                // 1.全部元素=前边元素+后边元素
                // f[k]= f[k-1]+f[k-2];
                // 2.因为后面有f[k-2]个元素 又可以拆分成
                //f[k-2]=f[k-3]+f[k-4]
                //全部元素=前边元素+后边元素
                // 即在f[k-2]的后面继续查找k-=2
                //下次循环 mid = f[k-1-2]-1;
                k -= 2;
            } else {
                //返回mid
                //需要确定返回哪个
                if (mid <= right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        return -1;
    }
}
