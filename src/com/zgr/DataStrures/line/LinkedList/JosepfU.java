package com.zgr.DataStrures.line.LinkedList;

import java.nio.file.FileStore;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 11:41 2020/1/13
 */
public class JosepfU {

    public static void main(String[] args) {
        //列表
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(5);
//        list.showLinked();

        list.countBoy(1, 2, 5);


    }

}

class CircleSingleLinkedList {
    private Boy first = null;

    //添加小孩，构建成环形列表
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums不正确");
            return;
        }
        Boy currBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy newBoy = new Boy(i);
            if (i == 1) {
                first = newBoy;
                first.setNext(first);
                currBoy = first;
            } else {
                currBoy.setNext(newBoy);
                newBoy.setNext(first);
                currBoy = newBoy;
            }
        }


    }
    //小孩出圈

    /**
     * @param StartNo  从第几个小孩开始
     * @param countNum 表示数几下
     * @param nums     表示小孩个数
     */
    public void countBoy(int StartNo, int countNum, int nums) {
        //校验
        if (first == null || StartNo > nums || StartNo < 1) {
            System.out.println("参数有误，请重新输入");
            return;
        }
        //辅助指针，指向first
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，移动helper和first StartNo-1次
        for (int i = 0; i < StartNo - 1; i++) {
            helper = helper.getNext();
            first = first.getNext();
        }
        while (true) {
            if (helper == first) {
                break;
            }
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩出圈%d\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d,", first.getNo());
    }

    //遍历当前环形列表
    public void showLinked() {
        if (first == null) {
            System.out.println("单前为空");
            return;
        }
        Boy currBoy = first;
        while (true) {
            System.out.printf("小孩编号%d：", currBoy.getNo());
            if (currBoy.getNext() == first) {
                break;
            }
            currBoy = currBoy.getNext();
        }
    }

}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                ", next=" + next +
                '}';
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}