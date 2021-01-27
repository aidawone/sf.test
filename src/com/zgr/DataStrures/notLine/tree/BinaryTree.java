package com.zgr.DataStrures.notLine.tree;

/**
 * 二叉树的遍历
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 18:13 2020/1/19
 */
public class BinaryTree {
    public static void main(String[] args) {
        BinaryTrees tree = new BinaryTrees();

        Hero root = new Hero(1, "宋江");
        Hero n2 = new Hero(2, "吴用");
        Hero n3 = new Hero(3, "卢俊义");
        Hero n4 = new Hero(4, "林冲");
        Hero n5 = new Hero(5, "徐盛");

        root.setLeft(n2);
        root.setRight(n3);
        n3.setRight(n4);
        n3.setLeft(n5);
        tree.setRoot(root);
        System.out.println("前序遍历:"); //1,2,3,4,5
        tree.preOrder();
        System.out.println("中序遍历:"); //2,1,5,3,4
        tree.infixOrder();
        System.out.println("后序遍历:");    //2,5,4,3,1
        tree.aferOrder();

        System.out.println("前序查找");
        Hero hero = tree.preOderSearch(5);
        if (hero != null) {
            System.out.printf("找到了id=%d,name=%s", hero.getId(), hero.getName());
        } else {
            System.out.println("没有找到");
        }
//        System.out.println("中序查找");
//        Hero hero1 = tree.infixOderSearch(4);
//        if(hero1!=null){
//            System.out.printf("找到了id=%d,name=%s",hero1.getId(),hero1.getName());
//        }else{
//            System.out.println("没有找到");
//        }
//        System.out.println("后序查找");
//        Hero hero2 = tree.afterOderSearch(4);
//        if(hero2!=null){
//            System.out.printf("找到了id=%d,name=%s",hero2.getId(),hero2.getName());
//        }else{
//            System.out.println("没有找到");
//        }

        //删除节点
        System.out.println("前序遍历");
        tree.preOrder();
        tree.deleteById(1);
        System.out.println("删除后");
        tree.preOrder();
    }
}

//二叉树
class BinaryTrees {
    private Hero root;

    public void setRoot(Hero root) {
        this.root = root;
    }

    //前序
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //中序
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //后序
    public void aferOrder() {
        if (this.root != null) {
            this.root.afterOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //前序查找
    public Hero preOderSearch(int id) {
        if (root != null) {
            return root.binarySearch(id);

        } else {
            return null;
        }

    }

    //中序查找
    public Hero infixOderSearch(int id) {
        if (root != null) {
            return root.binaryInfixSearch(id);

        } else {
            return null;
        }

    }

    //后序查找
    public Hero afterOderSearch(int id) {
        if (root != null) {
            return root.binaryafterSearch(id);

        } else {
            return null;
        }

    }

    //删除
    public void deleteById(int id) {
        if (root != null) {
            if (root.getId() == id) {
                root = null;
            } else {
                root.deleteNode(id);
            }
        } else {
            System.out.println("空树~~");
        }

    }

}


class Hero {
    private Integer id;
    private String name;
    private Hero left;
    private Hero right;

    public Hero(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hero getLeft() {
        return left;
    }

    public void setLeft(Hero left) {
        this.left = left;
    }

    public Hero getRight() {
        return right;
    }

    public void setRight(Hero right) {
        this.right = right;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void afterOrder() {
        if (this.left != null) {
            this.left.afterOrder();
        }

        if (this.right != null) {
            this.right.afterOrder();
        }
        System.out.println(this);
    }

    //前序查找
    public Hero binarySearch(Integer id) {
        System.out.println("进入前序查找");
        if (this.id == id) {
            return this;
        }
        Hero reshero = null;
        if (this.left != null) {
            reshero = this.left.binarySearch(id);
        }
        if (reshero != null) {
            return reshero;
        }
        if (this.right != null) {
            reshero = this.right.binarySearch(id);
        }
        return reshero;
    }

    //中序查找
    public Hero binaryInfixSearch(Integer id) {

        Hero reshero = null;
        if (this.left != null) {
            reshero = this.left.binarySearch(id);
        }
        if (reshero != null) {
            return reshero;
        }
        System.out.println("中序查找");
        if (this.id == id) {
            return this;
        }

        if (this.right != null) {
            reshero = this.right.binarySearch(id);
        }
        return reshero;
    }
    //后序查找


    public Hero binaryafterSearch(Integer id) {

        Hero reshero = null;
        if (this.left != null) {
            reshero = this.left.binarySearch(id);
        }
        if (reshero != null) {
            return reshero;
        }

        if (this.right != null) {
            reshero = this.right.binarySearch(id);
        }
        if (reshero != null) {
            return reshero;
        }
        System.out.println("进入后序查找");
        if (this.id == id) {
            return this;
        }
        return reshero;
    }

    //删除
    public void deleteNode(int id) {
        if (this.left != null && this.left.id == id) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.id == id) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.deleteNode(id);
        }
        if (this.right != null) {
            this.right.deleteNode(id);
        }

    }

}
