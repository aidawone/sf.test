package com.zgr.DataStrures.notLine.tree;

/**
 * 以数组的方式存储树
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 22:43 2020/1/19
 */
public class ArrBinaryTree {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTrees trees = new ArrBinaryTrees(arr);
        trees.preshowByTree(0);

    }

}

//编写一个ArrBinaryTrees 实现顺序二叉树遍历
class ArrBinaryTrees {
    private int[] arr;//存储数据节点

    public ArrBinaryTrees(int[] arr) {
        this.arr = arr;
    }


    //遍历
    public void preshowByTree(int index) {
        if (arr.length == 0 && arr == null) {
            System.out.println("数组为空，无法遍历");
        }
        //开始遍历
        System.out.println(arr[index]);
        if ((2 * index + 1) < arr.length) {
            preshowByTree(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            preshowByTree(2 * index + 2);
        }
    }


}

