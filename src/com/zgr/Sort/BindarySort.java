package com.zgr.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 20:59 2020/1/18
 */
public class BindarySort {
    public static void main(String[] args) {

        int arr[] = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1000, 1234};

//        int v=bindary(arr,0,arr.length-1,1);
//        System.out.println("下标是="+v);
        List<Integer> integers = bindaryOptim(arr, 0, arr.length - 1, 1000);
        System.out.println(integers);

    }

    /**
     * 返回查找值的下标
     *
     * @param arr   查找的数据集
     * @param left  左边的的索引
     * @param right 右边的索引
     * @param value 查找的值
     */
    public static int bindary(int[] arr, int left, int right, int value) {
        //没有该值得情况
        if (left > right) {
            return -1;
        }
        //中轴
        int mid = (left + right) / 2;

        int midval = arr[mid];
        //假设是从小到大的排序,说明值在右边,向右递归
        if (value > midval) {
            return bindary(arr, mid + 1, right, value);
        } else if (value < midval) {
            return bindary(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }
    //当一个有序的数组中有多个要查找的值。如何全部取出

    public static List<Integer> bindaryOptim(int[] arr, int left, int right, int value) {
        //没有该值得情况
        if (left > right) {
            return new ArrayList<>();
        }
        //中轴
        int mid = (left + right) / 2;
        int midval = arr[mid];
        //假设是从小到大的排序,说明值在右边,向右递归
        if (value > midval) {
            return bindaryOptim(arr, mid + 1, right, value);
        } else if (value < midval) {
            return bindaryOptim(arr, left, mid - 1, value);
        } else {
            //刚好是中轴值
            List<Integer> list = new ArrayList<>();
            //向mid左扫描
            int temp = mid - 1;
            while (true) {
                //说明已经到了最左边,或者只要有一个不等于value
                if (temp < 0 || arr[temp] != value) {
                    break;
                }
                //否则就是
                list.add(temp);
                temp -= 1;
            }
            //将找到中间的放入
            list.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != value) {
                    break;
                }
                list.add(temp);
                temp += 1;
            }
            return list;
        }
    }
}
