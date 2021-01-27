package com.zgr.Sort;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 21:48 2020/1/20
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9, 2, 5, 8, 10};
        System.out.println(arr.length / 2 - 1);
        heapSort(arr);
    }

    //4，10，8，5，9，2，5，8，6
    //第一次=[4, 9, 8, 5, 6, 2, 5, 8, 10]
    //第一次=[4, 9, 8, 5, 6]
    //编写一个堆排序的方法
    public static void heapSort(int[] arr) {

        //分步
//        adjustHeap(arr, 3, arr.length);
//        System.out.println("第一次=" + Arrays.toString(arr));
//        adjustHeap(arr, 1, arr.length);
//        System.out.println("第二次=" + Arrays.toString(arr));
//        adjustHeap(arr, 2, arr.length);
//        System.out.println("第三次=" + Arrays.toString(arr));
//        adjustHeap(arr, 0, arr.length);
//        System.out.println("第四次=" + Arrays.toString(arr));
        //从下往上，从左往右
        //arr.length/2-1 原理是堆排序是完全二叉树,那么就需要左子树和右子树，arr.length就是所有的节点，/2是左子树和右子树得出全部节点
        //-1是因为从下标0开始
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);

        }
        System.out.println("调整后的数组=" + Arrays.toString(arr));
        /**
         * 1将堆元素与末尾元素交换，将最大元素沉到数组末端
         * 2重新调整结构，使其满足大顶堆或小顶堆，然后再次执行1
         */
        int temp;
        for (int j = arr.length - 1; j >= 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);

        }
        System.out.println("排序后的数组=" + Arrays.toString(arr));
    }
    //先执行那个节点都没关系
    /**
     * 第一次=[4, 6, 8, 10, 9, 2, 5, 8, 5]
     * 第二次=[4, 10, 8, 8, 9, 2, 5, 6, 5]
     * 第三次=[10, 9, 8, 8, 4, 2, 5, 6, 5]
     * 第四次=[10, 9, 8, 8, 4, 2, 5, 6, 5]
     */

    /**
     * 编写一个将数组（二叉树），调整成一个大顶堆
     * 只能将一个子树的父节点调整
     *
     * @param arr    调整的数组
     * @param i      表示非叶子节点在其中的索引
     * @param length 表示对多少元素进行调整，length是逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        //将非叶子节点的值保存在临时变量中
        int temp = arr[i];
        //遍历左子树
        // k = i * 2 + 1 是非叶子节点的左子节点
        //这个for循环的目的是找到最大值。并且把他放在适当位置
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //判断左子树的左节点和右节点的大小
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                //指向右子节点
                k++;
            }
            //将较大值赋值给较小值
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;  //赋值给i,交换位置
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
}
