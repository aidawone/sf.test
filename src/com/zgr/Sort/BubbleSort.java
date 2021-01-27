package com.zgr.Sort;

import java.util.Arrays;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 10:38 2020/1/15
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] bubble = {9, 6, 10, -4, -6};
        int[] map = new int[80000];
        for (int i = 0; i < map.length; i++) {
            int v = (int) (Math.random() * 80000);
            map[i] = v;
        }
        System.out.println("执行前时间:");
        BubbleSort(map);


//        for(int i =0;i<bubble.length-1-1;i++){
//            if(bubble[i]>bubble[i+1]){
//                temp=bubble[i+1];
//                bubble[i+1]=bubble[i];
//                bubble[i]=temp;
//            }
//        }
//        System.out.println(Arrays.toString(bubble));
//        for(int i =0;i<bubble.length-1-2;i++){
//            if(bubble[i]>bubble[i+1]){
//                temp=bubble[i+1];
//                bubble[i+1]=bubble[i];
//                bubble[i]=temp;
//            }
//        }
//        System.out.println(Arrays.toString(bubble));
//        for(int i =0;i<bubble.length-1-3;i++){
//            if(bubble[i]>bubble[i+1]){
//                temp=bubble[i+1];
//                bubble[i+1]=bubble[i];
//                bubble[i]=temp;
//            }
//        }
//        System.out.println(Arrays.toString(bubble));
//        for(int i =0;i<bubble.length-1-4;i++){
//            if(bubble[i]>bubble[i+1]){
//                temp=bubble[i+1];
//                bubble[i+1]=bubble[i];
//                bubble[i]=temp;
//            }
//        }
//        System.out.println(Arrays.toString(bubble));
    }

    public static void BubbleSort(int[] bubble) {
        long l = System.currentTimeMillis();
        int temp = 0;
        boolean flag = false;

        for (int j = 0; j < bubble.length - 1; j++) {
            for (int i = 0; i < bubble.length - 1 - j; i++) {
                if (bubble[i] > bubble[i + 1]) {
                    flag = true;
                    temp = bubble[i + 1];
                    bubble[i + 1] = bubble[i];
                    bubble[i] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
        long after = System.currentTimeMillis();
        System.out.println("执行完花的时间：" + (after - l));
//        System.out.println(Arrays.toString(bubble));
    }
}
