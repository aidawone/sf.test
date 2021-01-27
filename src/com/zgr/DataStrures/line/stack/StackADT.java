package com.zgr.DataStrures.line.stack;

public interface StackADT {
    //入栈操作
    public void push(Object element);

    //出栈操作
    public Object pop();

    //判断是否为空
    public boolean isEmpty();

    //得到当前栈的大小
    public int size();

    //返回栈顶对象的引用
    public Object peek();

    //转化为字符串
    public String toString();

}