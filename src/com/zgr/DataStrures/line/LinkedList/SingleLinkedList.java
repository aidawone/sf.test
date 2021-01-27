package com.zgr.DataStrures.line.LinkedList;

import java.util.Stack;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 13:16 2020/1/12
 */
public class SingleLinkedList {
    public static void main(String[] args) {
        HearNode hero1 = new HearNode(1, "宋江", "及时雨");
        HearNode hero2 = new HearNode(2, "吴用", "智多星");
        HearNode hero3 = new HearNode(3, "卢俊义", "玉麒麟");
        HearNode hero4 = new HearNode(4, "林冲", "豹子头");
        HearNode hero5 = new HearNode(4, "冬游怪咖", "奥利给");

        SingleLinkedListDemo list = new SingleLinkedListDemo();
        list.addByOrder(hero3);
        list.addByOrder(hero4);
        list.addByOrder(hero1);
        list.addByOrder(hero2);
        list.showLinked();
        //反转之后
        System.out.println("反转：");
        reversal(list.getHeader());
        list.showLinked();

        System.out.println("修改之后：");
        list.update(hero5);
        list.showLinked();
        System.out.println("删除之后");
        list.delete(1);
        list.showLinked();
        System.out.println(getLength(list.getHeader()));
        System.out.println("获得倒数第2个数是:" + findLastByIndex(list.getHeader(), 2));

        System.out.println("逆序打印：");
        reversalPrint(list.getHeader());
    }

    //通过栈的特性来逆序打印链表
    public static void reversalPrint(HearNode hearNode) {
        if (hearNode.next == null) {
            return;
        }
        HearNode temp = hearNode.next;
        Stack<HearNode> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }

    }

    /**
     * 获取链表中有效的节点
     *
     * @param hearNode 头节点
     * @return
     */
    public static int getLength(HearNode hearNode) {
        if (hearNode.next == null) {
            return 0;
        }
        HearNode temp = hearNode.next;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    //查找单链表的倒数第k个节点
    /*思路：
      1.编写一个方法，接收节点，同时接收一个index
      2.index表示是倒数第index个节点
      3.先把链表从头到尾遍历，得到链表的有效长度
      4.得到size后，我们从链表的第一个开始遍历（size-index）次
      5.有则返回，无则返回null
    */
    public static HearNode findLastByIndex(HearNode hearNode, int index) {
        if (hearNode.next == null) {
            return null;
        }
        int size = getLength(hearNode);
        if (index <= 0 || index > size) {
            return null;
        }
        HearNode temp = hearNode.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;

    }

    //反转链表
    public static void reversal(HearNode hearNode) {
        if (hearNode.next == null || hearNode.next.next == null) {
            return;
        }
        //定义一个新的链表
        HearNode reversal = new HearNode(0, "", "");
        //辅助链表
        HearNode temp = hearNode.next;
        //保存下一个指针
        HearNode next = null;
        while (temp != null) {
            //保存下一个
            next = temp.next;
            //将原先在reversal中的所有值后移一位，且让新加入的值指向旧reversal.next中的值
            temp.next = reversal.next;
            //将当前辅助的指针移动reversal的head.next,也就是最前端
            reversal.next = temp;
            temp = next;
        }
        hearNode.next = reversal.next;
    }

}


class SingleLinkedListDemo {
    //头结点不能动

    public HearNode getHeader() {
        return header;
    }

    private HearNode header = new HearNode(0, "", "");

    //增加到链表的尾部
    public void addLinked(HearNode hearNode) {
        //因为头节点不能动，需要一个辅助
        HearNode temp = header;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = hearNode;
    }

    //顺序添加，不能添加重复编号的
    public void addByOrder(HearNode hearNode) {
        HearNode temp = header;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no_id > hearNode.no_id) {
                break;
            }
            if (temp.next.no_id == hearNode.no_id) {
                flag = true;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("该英雄已经有了,不能重复添加%d\n", temp.no_id);

        } else {
            hearNode.next = temp.next;
            temp.next = hearNode;
        }


    }


    //删除
    public void delete(int no) {
        HearNode temp = header;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no_id == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到该节点 %d", no);
        }

    }

    //修改
    public void update(HearNode hearNode) {
        if (header.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HearNode temp = header.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                //到了链表的尾部
                break;
            }
            if (temp.no_id == hearNode.no_id) {
                flag = true;
                break;
            }
            temp = temp.next;

        }
        if (flag) {
            temp.name = hearNode.name;
            temp.nickname = hearNode.nickname;
        } else {
            System.out.printf("没有该英雄%d", hearNode.no_id);
        }


    }

    //显示链表
    public void showLinked() {
        //判断链表是否为空
        if (header.next == null) {
            System.out.println("链表为空！");
        }
        HearNode temp = header.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

class HearNode {
    public int no_id;
    public String name;
    public String nickname;
    HearNode next;

    public HearNode(int no_id, String name, String nickname) {
        this.no_id = no_id;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HearNode{" +
                "no_id=" + no_id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

