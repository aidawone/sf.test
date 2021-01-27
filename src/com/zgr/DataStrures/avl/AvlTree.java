package com.zgr.DataStrures.avl;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 13:17 2020/1/22
 */
public class AvlTree {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};
        int[] arr2 = {10, 12, 8, 9, 7, 6};
        int[] arr3 = {10, 11, 7, 6, 8, 9};
        AvlTrees t = new AvlTrees();
        for (int i = 0; i < arr.length; i++) {
            t.sortAdd(new SNode(arr3[i]));
        }
        t.infixOrder();
        int i = t.getRoot().recoverHeight();
        System.out.println("当前节点的高度=" + i);
        System.out.println("当前节点左子树的高度=" + t.getRoot().leftHeight());
        System.out.println("当前节点右子树的高度=" + t.getRoot().rightHeight());
        System.out.println(t.getRoot().right.left);
        System.out.println(t.getRoot().left.left);
    }
}

class AvlTrees {
    public SNode root;

    //添加方法
    public void sortAdd(SNode node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public SNode getRoot() {
        return root;
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

    //右旋转
    public void rightRotate() {
        SNode newNode = new SNode(value);
        //把newNode左子节点指向当前节点的左子节点的 右子节点
        newNode.left = left.right;
        //把newNode右子节点指向当前节点右子节点
        newNode.right = right;
        //将当前节点的值
        value = left.value;
        //将当前节点的值左子节点指向当前节点的左子节点的左子节点
        left = left.left;
        //将当前节点的值右子节点指向new
        right = newNode;
    }

    //左旋转问题
    public void leftRotate() {
        //创建新的节点，以当前根节点的值
        SNode newNode = new SNode(value);
        //将新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成当前节点右子树的右子树
        right = right.right;
        //把当前节点的左子树设置成新节点
        left = newNode;
    }

    //返回左子树
    public int leftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.recoverHeight();
        }
    }

    //返回右子树
    public int rightHeight() {
        if (right == null) {
            return 0;

        } else {
            return right.recoverHeight();
        }
    }

    //返回当前节点的高度
    public int recoverHeight() {
        return Math.max(left == null ? 0 : left.recoverHeight(), right == null ? 0 : right.recoverHeight()) + 1;
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
        if (rightHeight() - leftHeight() > 1) {
            //如果当前节点的右子结点的左子树大于右子树高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();//左旋转
            }
            return;
        }
        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前节点的左子节点进行旋转
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();//左旋转
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