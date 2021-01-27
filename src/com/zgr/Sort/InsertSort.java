package com.zgr.Sort;

import java.util.Arrays;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 15:09 2020/1/15
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        insertSort(arr);
    }
    //插入排序：
    //遍历查询插入的位置
    //空出一位作为插入的虚拟集合
    //后面的则为待插入的集合
    //遍历的自然就是待插入的集合

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertIndex < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //退出循环的时候说明找到了插入的位置
            arr[insertIndex + 1] = insertValue;
            System.out.println(Arrays.toString(arr));
        }


    }
}
