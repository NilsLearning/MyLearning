package indi.qun.learning.$01_arithmetic.part_02;

/**
 * 实现二叉树的三种遍历方式
 */

public class MyBinaryTree {
    /**
     * 存放数据
     */
    private Integer data;
    /**
     * 左节点
     */
    private MyBinaryTree left;
    /**
     * 右节点
     */
    private MyBinaryTree right;

    public MyBinaryTree() {
    }

    public MyBinaryTree(Integer data, MyBinaryTree left, MyBinaryTree right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    public MyBinaryTree(Integer data) {
        this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public MyBinaryTree getLeft() {
        return left;
    }

    public void setLeft(MyBinaryTree left) {
        this.left = left;
    }

    public MyBinaryTree getRight() {
        return right;
    }

    public void setRight(MyBinaryTree right) {
        this.right = right;
    }

    public static void main(String[] args) {
        MyBinaryTree myBinaryTree = new MyBinaryTree();
        MyBinaryTree myBinaryTree1 = myBinaryTree.creatMyBinaryTree1();
//        myBinaryTree.preOrder(myBinaryTree1);
//        System.out.println("前序遍历结束");
//        myBinaryTree.inOrder(myBinaryTree1);
//        System.out.println("中序遍历结束");
//        myBinaryTree.postOrder(myBinaryTree1);
//        System.out.println("后序遍历结束");
        // 查找二叉树节点
//        MyBinaryTree myBinaryTree2 = myBinaryTree.find(4, myBinaryTree1);
////        System.out.println(myBinaryTree2);
        // 插入二叉树节点
        MyBinaryTree myBinaryTree2=null;
        MyBinaryTree insert = myBinaryTree.insert(3, myBinaryTree2);
        MyBinaryTree insert1 = myBinaryTree.insert(2, insert);
        MyBinaryTree insert2 = myBinaryTree.insert(1, insert1);
        MyBinaryTree insert3 = myBinaryTree.insert(4, insert2);
        MyBinaryTree insert4 = myBinaryTree.insert(5, insert3);
        myBinaryTree.preOrder(insert4);
//        System.out.println(insert4);


    }

    @Override
    public String toString() {
        return "MyBinaryTree{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    MyBinaryTree creatMyBinaryTree() {
        Integer[] nums = {1, 2, 3, 4, 5};
        MyBinaryTree myBinaryTreeB = new MyBinaryTree();
        MyBinaryTree myBinaryTreeD = new MyBinaryTree();
        MyBinaryTree myBinaryTreeE = new MyBinaryTree();
        myBinaryTreeD.setData(nums[3]);
        myBinaryTreeE.setData(nums[4]);
        MyBinaryTree myBinaryTreeC = new MyBinaryTree(nums[2], myBinaryTreeD, myBinaryTreeE);
        myBinaryTreeB.setData(nums[1]);
        MyBinaryTree myBinaryA = new MyBinaryTree(nums[0], myBinaryTreeB, myBinaryTreeC);
        return myBinaryA;
    }

    MyBinaryTree creatMyBinaryTree1() {
        MyBinaryTree myBinaryTree3 = new MyBinaryTree(3);
        MyBinaryTree myBinaryTree2 = new MyBinaryTree(2);
        MyBinaryTree myBinaryTree1 = new MyBinaryTree(1);
        MyBinaryTree myBinaryTree4 = new MyBinaryTree(4);
        MyBinaryTree myBinaryTree5 = new MyBinaryTree(5);
        myBinaryTree3.setLeft(myBinaryTree2);
        myBinaryTree2.setLeft(myBinaryTree1);
        myBinaryTree3.setRight(myBinaryTree4);
        myBinaryTree4.setRight(myBinaryTree5);
        return myBinaryTree3;
    }


    /**
     * 前序遍历
     *
     * @param binaryTree
     */
    void preOrder(MyBinaryTree binaryTree) {
        if (binaryTree == null) return;
        System.out.println(binaryTree.data);
        preOrder(binaryTree.left);
        preOrder(binaryTree.right);
    }


    /**
     * 中序遍历
     *
     * @param binaryTree
     */
    void inOrder(MyBinaryTree binaryTree) {
        if (binaryTree == null) return;
        inOrder(binaryTree.left);
        System.out.println(binaryTree.data);
        inOrder(binaryTree.right);
    }

    /**
     * 后序遍历
     *
     * @param binaryTree
     */
    void postOrder(MyBinaryTree binaryTree) {
        if (binaryTree == null) return;
        postOrder(binaryTree.left);
        postOrder(binaryTree.right);
        System.out.println(binaryTree.data);
    }

    /**
     * 二叉树的寻找操作
     * @param data
     * @param binaryTree
     * @return
     */
    MyBinaryTree find(int data,MyBinaryTree binaryTree) {
        MyBinaryTree p = binaryTree;
        while(p!=null){
            if(p.data >data){
                p = p.left;
            }else if(p.data <data){
                p = p.right;
            }else{
                return p;
            }
        }
       return null;
    }

    /**
     * 二叉树的插入操作
     * @param data
     * @param binaryTree
     * @return
     */
    MyBinaryTree insert(int data,MyBinaryTree binaryTree){
        if(binaryTree == null){
            binaryTree = new MyBinaryTree(data);
            return binaryTree;
        }

        MyBinaryTree p = binaryTree;
        while (p !=null){
            // 如果
            if(p.data < data){
                if(p.right == null){
                    MyBinaryTree right = new MyBinaryTree(data);
                    p.setRight(right);
                    return binaryTree;
                }else{
                    p=p.right;
                }
            }else {
                if(p.left == null){
                    MyBinaryTree left = new MyBinaryTree(data);
                    p.setLeft(left);
                    return binaryTree;
                }else{
                    p=p.left;
                }
            }
        }
        return binaryTree;
    }
    
    

}


