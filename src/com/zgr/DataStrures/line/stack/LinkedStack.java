package com.zgr.DataStrures.line.stack;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 14:02 2020/1/13
 */
//基于链表实现栈，也就是以节点的形式存储栈，从头节点往外取出数据，这样好处理

public class LinkedStack implements StackADT {

    // 链表节点类，内部类实现，包含数据域和指针域
    class Node {
        int data; // 数据域
        Node next;// 指针域

        public Node(int data) {
            this.data = data;
        }
    }

    private Node top;
    private int count;

    public LinkedStack() {
        top = null;
        count = 0;
    }

    @Override
    public void push(Object element) {
        Node node = new Node((Integer) element);
        //倒序构造链表，最先入栈的是链表尾部，然后最后入栈的是链表头部，这样指针指向关系容易实现，虽然逻辑不好理解
        //因为我们的理解一般是从头部开始构造节点，而从尾部开始构造节点比较抽象
        node.next = top;
        //指向的是头结点
        top = node;
        count++;
    }

    @Override
    public Object pop() {
        if (isEmpty()) {
            System.out.println("stack is empty!");
            System.exit(1);
        }
        Object result = top.data;
        top = top.next;
        count--;
        return result;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Object peek() {
        Object result = top.data;
        return result;
    }

}
