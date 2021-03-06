package com.zgr.DataStrures.line;

import java.util.Scanner;

/**
 * 数组模拟队列
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 10:13 2020/1/12
 */
public class ArrayQueue {
    public static void main(String[] args) {
        ArrayQueueDemo demo = new ArrayQueueDemo(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean doop = true;
        while (doop) {
            System.out.println("请输入一个字母:");
            System.out.println("s(show):显示队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("e(show):退出程序");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头部");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    demo.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入要添加的值：");
                    int i = scanner.nextInt();
                    demo.addQueue(i);
                    break;
                case 'e':
                    scanner.close();
                    doop = false;
                    break;
                case 'g':
                    try {
                        int que = demo.getQue();
                        System.out.printf("取出的数据是%d=", que);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int que = demo.headQueue();
                        System.out.printf("取出的头数据是%d\n", que);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;

            }


        }
        System.out.println("程序退出！");
    }
}

class ArrayQueueDemo {
    private int maxSize;
    private int front;
    private int real;
    private int[] arr;

    public ArrayQueueDemo(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        real = -1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == real;
    }

    //判断队列是否满
    public boolean isFull() {
        return real == maxSize - 1;
    }

    //加数据
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满,不能加入");
            return;
        }
        real++;
        arr[real] = n;
    }

    //取数据
    public int getQue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取出数据!");

        }
        front++;
        return arr[front];
    }

    //显示数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空,不能取出数据!");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //查看头部数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取出数据!");
        }
        return arr[front + 1];

    }
}
