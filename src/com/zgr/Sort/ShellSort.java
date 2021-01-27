package com.zgr.Sort;

import java.util.Arrays;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 15:48 2020/1/15
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shell(arr);
        ShellSortDiration(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shell(int[] arr) {
        //交换法
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //第一次分成length/5
            for (int i = gap; i < arr.length; i++) {
                //一组两个,交换依次,step=5
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("第" + gap + "轮：" + Arrays.toString(arr));
        }


//        //第二次分成length/2
//        for(int i=2;i<arr.length;i++){
//            //一组5个,交换依次,step=5
//            for(int j=i-2;j>=0;j-=2){
//                if(arr[j]>arr[j+2]){
//                    temp=arr[j];
//                    arr[j]=arr[j+2];
//                    arr[j+2]=temp;
//                }
//            }
//
//
//        }
//        System.out.println("第二轮："+ Arrays.toString(arr));
//
//        //第三次分成length/2
//        for(int i=1;i<arr.length;i++){
//            //一组两个,交换依次,step=5
//            for(int j=i-1;j>=0;j-=1){
//                if(arr[j]>arr[j+1]){
//                    temp=arr[j];
//                    arr[j]=arr[j+1];
//                    arr[j+1]=temp;
//                }
//            }
//        }
//        System.out.println("第三轮："+ Arrays.toString(arr));
    }

    //对希尔排序交换法优化->移位法
    public static void ShellSortDiration(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //保存等待区前面的数的下标
                int tempIndex = i;
                //保存等待区数据
                int temp = arr[tempIndex];
                if (arr[tempIndex] < arr[tempIndex - gap]) {
                    while (tempIndex - gap >= 0 && temp < arr[tempIndex - gap]) {
                        //前面的往后移
                        arr[tempIndex] = arr[tempIndex - gap];
                        tempIndex -= gap;
                    }
                    //退出之后就是插入的位置
                    arr[tempIndex] = temp;
                }
            }

        }


    }

}
