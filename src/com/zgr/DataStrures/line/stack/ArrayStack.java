package com.zgr.DataStrures.line.stack;

import java.util.Scanner;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 13:18 2020/1/13
 */
public class ArrayStack {
    public static void main(String[] args) {
        ArryStackDemo list = new ArryStackDemo(4);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean doop = true;
        while (doop) {
            System.out.println("请输入一个字母:");
            System.out.println("s(show):显示栈");
            System.out.println("p(push):添加数据到栈");
            System.out.println("e(show):退出程序");
            System.out.println("o(pop):取出栈数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    list.list();
                    break;
                case 'p':
                    System.out.println("请输入加入的值");
                    int value = scanner.nextInt();
                    list.push(value);
                    break;
                case 'e':
                    System.exit(0);
                    doop = false;
                    break;
                case 'o':
                    try {
                        int que = list.pop();
                        System.out.printf("取出的数据是%d=", que);
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

class ArryStackDemo {
    public int maxSize;
    public int top;
    public int[] stack;

    public ArryStackDemo(int maxSize) {
        this.maxSize = maxSize;
        top = -1;
        stack = new int[maxSize];
    }

    //入栈
    public void push(int num) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = num;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);

        }
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }


}
