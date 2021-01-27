package com.zgr.DataStrures.notLine.tree;

/**
 * 中序线索化二叉树
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 23:22 2020/1/19
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //线索化
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        tree.ThreadBinary();
        //遍历线索化
        tree.ThreadBinaryList();
        System.out.println(tree);
    }
}

class ThreadedBinaryTree {
    private HeroNode root;
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
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
    public HeroNode preOderSearch(int id) {
        if (root != null) {
            return root.binarySearch(id);
        } else {
            return null;
        }

    }

    //中序查找
    public HeroNode infixOderSearch(int id) {
        if (root != null) {
            return root.binaryInfixSearch(id);

        } else {
            return null;
        }

    }

    public void ThreadBinary() {
        this.ThreadBinary(root);

    }

    //后序查找
    public HeroNode afterOderSearch(int id) {
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


    //编写对二叉树进行线索化
    public void ThreadBinary(HeroNode node) {
        if (node == null) {
            return;
        }
        //先线索化左子数
        ThreadBinary(node.getLeft());
        //当前节点
        //前驱节点
        if (node.getLeft() == null) {
            node.setLeftType(1);
            node.setLeft(pre);
        }
        //后继节点
        //pre移动到当前节点
        //且当前节点移动到后继节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        //右子树
        ThreadBinary(node.getRight());
    }

    //遍历线索化二叉树--中序
    //结果为8,3,10,1,14,6
    public void ThreadBinaryList() {
        //辅助指针
        HeroNode node = root;
        while (node != null) {
            //遍历左
            //通过leftType和rightType分辨是否线索化
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //线索化
            System.out.println(node);
            //右边
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            while (node.getRightType() == 1) {
                //线索化
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

}

class HeroNode {
    private Integer id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    private int leftType;
    private int RightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return RightType;
    }

    public void setRightType(int rightType) {
        RightType = rightType;
    }

    public HeroNode(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
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
    public HeroNode binarySearch(Integer id) {
        System.out.println("进入前序查找");
        if (this.id == id) {
            return this;
        }
        HeroNode reshero = null;
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
    public HeroNode binaryInfixSearch(Integer id) {

        HeroNode reshero = null;
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


    public HeroNode binaryafterSearch(Integer id) {

        HeroNode reshero = null;
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

