package com.zgr.DataStrures.notLine.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 10:28 2020/1/21
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node hefuman = createHefuman(arr);
        preOrder(hefuman);

    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树");
        }
    }

    //创建赫夫曼树的方法
    public static Node createHefuman(int[] arr) {
        //1遍历数组
        //2加入到arr中
        List<Node> list = new ArrayList<>();
        for (int value : arr) {
            list.add(new Node(value));
        }


        while (list.size() > 1) {
            Collections.sort(list);
            // System.out.println("排序后："+list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);

        }
        return list.get(0);
    }
}

//实现compare接口使用collections
class Node implements Comparable<Node> {
    public int value;
    public Node left;
    public Node right;

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
