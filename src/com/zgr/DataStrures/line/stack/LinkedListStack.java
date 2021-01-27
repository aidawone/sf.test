package com.zgr.DataStrures.line.stack;

import sun.applet.Main;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 14:04 2020/1/13
 */
public class LinkedListStack {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        stack.push(2);
        stack.push(5);
        stack.push(2);
        stack.push(7);
        stack.push(5);
        stack.push(9);
        Object pop = stack.pop();
        System.out.println(pop);
        Object po2p = stack.pop();
        System.out.println(po2p);
        Object pop3 = stack.pop();
        System.out.println(pop3);
    }
}
