package com.zgr.DataStrures.notLine;


import java.util.Scanner;

/**
 * 哈希表
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 13:33 2020/1/19
 */
public class HashTable {
    public static void main(String[] args) {

        HashTableDemo hashTable = new HashTableDemo(7);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean doop = true;
        while (doop) {
            System.out.println("请输入一个字母:");
            System.out.println("s(show):显示雇员");
            System.out.println("f(find):查找雇员");
            System.out.println("a(add):添加雇员");
            System.out.println("e(show):退出程序");
            System.out.println("d(del):删除雇员");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    hashTable.list();
                    break;
                case 'a':
                    System.out.println("请输入要添加的id：");
                    int id = scanner.nextInt();
                    System.out.println("请输入要添加的名字：");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case 'e':
                    scanner.close();
                    System.exit(0);
                    doop = false;
                    break;
                case 'f':
                    System.out.println("请输入要查找的id：");
                    int idSearch = scanner.nextInt();
                    hashTable.findByEmpId(idSearch);
                    break;
                case 'd':
                    System.out.println("请输入要删除的id：");
                    int Iddel = scanner.nextInt();
                    hashTable.deleteByEmpId(Iddel);
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}

//哈希表
//管理多条链表
class HashTableDemo {
    EmpLinkedList[] empLinkedList;
    private int size;

    public HashTableDemo(int size) {
        this.size = size;
        empLinkedList = new EmpLinkedList[size];
        //初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedList[i] = new EmpLinkedList();
        }
    }

    //添加
    public void add(Emp emp) {
        int i = hashFun(emp.id);
        empLinkedList[i].add(emp);

    }

    //遍历
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedList[i].list(i);
        }
    }

    //编写散列函数
    public int hashFun(int id) {
        return id % size;
    }

    //输入id，查找emp
    public void findByEmpId(int id) {
        int hashFun = hashFun(id);
        Emp seach = empLinkedList[hashFun].seach(id);
        if (seach != null) {
            System.out.println("在" + hashFun + "链表中找到该雇员");
        } else {
            System.out.println("没有该雇员");
        }

    }

    //根据id删除
    public void deleteByEmpId(int id) {
        int hashFun = hashFun(id);
        empLinkedList[hashFun].deletes(id);
    }
}


class Emp {
    public Integer id;
    public String name;
    public Emp next;

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Emp(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList {
    private Emp head;

    //添加雇员信息
    //添加到尾部,
    public void add(Emp emp) {
        //判断是否是第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
//        如果不是第一个,找到最后一个
        //需要一个辅助指针
        Emp temp = head;
        while (true) {
            //说明到了最后一个
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = emp;
    }

    //遍历链表
    public void list(int i) {
        if (head == null) {
            //链表为空
            System.out.println("当前" + i + "链表为空！！");
            return;
        }
        System.out.print("当前" + i + "链表的信息为：");
        Emp temp = head;
        while (true) {
            System.out.printf("=>id=%d name=%s\t", temp.id, temp.name);
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        System.out.println();
    }

    //查找
    //根据id查找
    public Emp seach(int id) {
        if (head == null) {
            System.out.println("链表为空！");
            return null;
        }
        //辅助指针
        Emp temp = head;
        while (true) {
            if (temp.id == id) {
                break;
            }
            if (temp.next == null) {
                //到了尾部
                temp = null;
                break;
            }
            temp = temp.next;
        }
        return temp;
    }

    //删除
    public void deletes(int id) {
        if (head == null) {
            System.out.println("链表为空！");
            return;
        }
        //辅助
        Emp emp = head;
        boolean flag = false;
        while (true) {
            if (emp.id == id) {
                //找到
                flag = true;
                break;
            }
            if (emp.next == null) {
                break;
            }
            emp = emp.next;
        }
        if (flag) {
            emp.next = head;
            System.out.println("删除成功");
        } else {
            System.out.println("没有该链表");
        }
    }
}

