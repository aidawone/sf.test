package com.zgr.DataStrures.line.LinkedList;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 20:46 2020/1/12
 */
public class DoubleLinkedList {
    public static void main(String[] args) {

        System.out.println("双向链表：");
        HearNode2 hero1 = new HearNode2(3, "宋江", "及时雨");
        HearNode2 hero2 = new HearNode2(4, "吴用", "智多星");
        HearNode2 hero3 = new HearNode2(5, "卢俊义", "玉麒麟");
        HearNode2 hero5 = new HearNode2(2, "冬游怪咖", "奥利给");
        DoubleLinkedListDemo list = new DoubleLinkedListDemo();
//        list.addLinked(hero1);
//        list.addLinked(hero2);
//        list.addLinked(hero3);
//        list.addLinked(hero5);
//        list.showLinked();
//        System.out.println("修改");
//        HearNode2 hero7= new HearNode2(4,"公孙胜","入云龙");
//        list.update(hero7);
//        list.showLinked();
//        System.out.println("删除");
//        list.delete(3);
//        list.showLinked();

        System.out.println("顺序添加");
        list.addByOrder(hero1);
        list.addByOrder(hero2);
        list.addByOrder(hero3);
        list.addByOrder(hero5);
        list.showLinked();


    }
}

class DoubleLinkedListDemo {
    public HearNode2 getHeader() {
        return header;
    }

    private HearNode2 header = new HearNode2(0, "", "");

    //显示链表
    public void showLinked() {
        //判断链表是否为空
        if (header.next == null) {
            System.out.println("链表为空！");
        }
        HearNode2 temp = header.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加
    //增加到链表的尾部
    public void addLinked(HearNode2 hearNode) {
        //因为头节点不能动，需要一个辅助
        HearNode2 temp = header;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = hearNode;
        hearNode.pre = temp;
    }

    public void update(HearNode2 hearNode) {
        if (header.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HearNode2 temp = header.next;
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

    //删除
    public void delete(int no) {

        if (header.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HearNode2 temp = header.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no_id == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("没有找到该节点 %d", no);
        }

    }

    //顺序添加，不能添加重复编号的
    public void addByOrder(HearNode2 hearNode) {
        HearNode2 temp = header;
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
            hearNode.pre = temp;
            temp.next = hearNode;
            temp.next.pre = hearNode;

        }
    }
}

class HearNode2 {
    public int no_id;
    public String name;
    public String nickname;
    HearNode2 next;
    HearNode2 pre;

    public HearNode2(int no_id, String name, String nickname) {
        this.no_id = no_id;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HearNode2{" +
                "no_id=" + no_id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

