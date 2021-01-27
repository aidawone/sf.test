package com.zgr.DataStrures.notLine.tree;

/**
 * 二叉排序树
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 21:19 2020/1/21
 */
public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySrotTree tree = new BinarySrotTree();
        for (int i = 0; i < arr.length; i++) {
            tree.sortAdd(new SNode(arr[i]));
        }
        tree.infixOrder();
        tree.deleteSearch(2);
        tree.deleteSearch(5);
        tree.deleteSearch(9);
        tree.deleteSearch(12);
        tree.deleteSearch(7);
        tree.deleteSearch(3);
        tree.deleteSearch(10);
        tree.deleteSearch(1);

        System.out.println("删除节点后：");

        tree.infixOrder();
    }

}

class BinarySrotTree {
    public SNode root;

    //添加方法
    public void sortAdd(SNode node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //排序
    public void infixOrder() {
        if (root != null) {
            this.root.list();
        } else {
            System.out.println("空数！");
        }
    }

    //封装查找方法
    public SNode search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }

    }

    //封装查找父节点
    public SNode searchParent(int value) {
        if (root == null) {
            return null;

        } else {
            return root.searchSNodeParent(value);
        }

    }

    //查找最小值
    public int searchMin(SNode node) {
        SNode temp = node;
        while (temp.left != null) {
            temp = temp.left;
        }
        //这是就指向了最小节点
        deleteSearch(temp.value);
        return temp.value;

    }

    //删除节点
    public void deleteSearch(int value) {
        if (root == null) {
            return;
        } else {
            //查找要删除的节点
            SNode searchChild = search(value);
            //没有找到要删除的节点
            if (searchChild == null) {
                return;
            }
            //说明要删除的节点就是root
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //查找要删除的节点的父节点
            SNode parent = searchParent(value);
            //判断要删除的叶子节点是父节点的左子节点还是右子节点
            if (searchChild.left == null && searchChild.right == null) { //删除叶子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (searchChild.left != null && searchChild.right != null) {//删除两个节点的子树
                //右子节点--------右边求最小值
                //左子节点-------左边求最大值
                int minVal = searchMin(searchChild.right);
                searchChild.value = minVal;

            } else {//剩下一种情况：那就是只有一个节点的子树
                //如果要删除的节点有左子节点
                if (searchChild.left != null) {
                    if (parent != null) {
                        //判断是父节点的左子树还是右子树
                        if (parent.left.value == value) {
                            parent.left = searchChild.left;
                        } else {
                            parent.right = searchChild.left;
                        }
                    } else {
                        root = searchChild.left;
                    }
                } else {
                    if (parent != null) {
                        //要删除的节点有右子节点
                        if (parent.left.value == value) { //判断是父节点的左子树还是右子树
                            parent.left = searchChild.right;
                        } else {
                            parent.right = searchChild.right;
                        }
                    } else {
                        root = searchChild.right;
                    }
                }

            }

        }

    }
}

//节点
class SNode {
    int value;
    SNode left;
    SNode right;

    @Override
    public String toString() {
        return "SNode{" +
                "value=" + value +
                '}';
    }

    public SNode(int value) {
        this.value = value;
    }

    //添加
    public void add(SNode node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;

            } else {
                this.right.add(node);
            }
        }

    }

    //中序遍历
    public void list() {
        if (this.left != null) {
            this.left.list();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.list();
        }
    }

    /**
     * 查找要删除的节点
     *
     * @param value 节点的值
     * @return 要删除的节点
     */
    public SNode search(int value) {
        if (value == this.value) {
            //就是当前节点
            return this;
        } else if (value < this.value) {
            //小于，左递归
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;

            }
            return this.right.search(value);
        }

    }

    //查找要删除节点的父节点
    public SNode searchSNodeParent(int value) {
        //左子树不为空且刚好等于
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //左递归
            if (this.left != null && value < this.value) {
                return this.left.searchSNodeParent(value);
            } else if (this.right != null && value >= this.value) {
                return this.right.searchSNodeParent(value);
            } else {
                System.out.println("没有找到父节点");
                return null;
            }
        }
    }
}
