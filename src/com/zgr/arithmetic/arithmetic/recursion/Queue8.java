package com.zgr.arithmetic.arithmetic.recursion;

/**
 * 八皇后问题
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 22:42 2020/1/14
 */
public class Queue8 {
    int max = 8;
    //下标表示棋盘上的行
    private int[] arry = new int[max];

    public static void main(String[] args) {

    }

    /**
     * 查看当我们放置第n个皇后时，就去检测该皇后是否和前面以及摆放的皇后冲突
     *
     * @param n 表示第几个皇后
     * @return 通过与n-1判断是否合适
     */
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //arry[i]==arry[n] 表示同一列
            //Math.abs(n-i)==Math.abs(arry[n]-arry[i])
            //表示在同一斜线，这个是使用等腰三角形的倍率
            if (arry[i] == arry[n] || Math.abs(n - i) == Math.abs(arry[n] - arry[i])) {
                return false;
            }
        }
        return true;
    }

    //编写一个方法，放置第n个皇后
    private void check(int n) {
        //第8个皇后，说明棋盘上已经有8个皇后了
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            arry[n] = i;

        }

    }

    private void print() {
        for (int i = 0; i < arry.length; i++) {
            System.out.println(arry[i] + "");
        }
        System.out.println();
        System.out.println();

    }
}
