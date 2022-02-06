package indi.qun.learning.$01_arithmetic.part_02;



/**
 * 学习目标:
 * 1.Java实现一个单链表，Java当中是没有单链表的
 * 2.单链表反转
 * 3.链表中环的检测  -1) 快慢指针  2)hashMap找重
 * 4.两个有序链表合并
 * 5.删除链表倒数第n个节点 [deleteNode1]自己写成了删除倒数几个节点
 * 6.求单链表的中间节点    -1)快慢指针
 */
public class SinglyLinkedList {
    public static void main(String[] args) {
        MySingleLinkedList mySingleLinkedList = new MySingleLinkedList();
        mySingleLinkedList.add(1);
        mySingleLinkedList.add(2);
        mySingleLinkedList.add(3);
        mySingleLinkedList.add(4);
        mySingleLinkedList.add(5);
        System.out.println("原始链表内容:" + mySingleLinkedList);
        // 单链表反转
//        MySingleLinkedList.Node reverse = MySingleLinkedList.reverse(mySingleLinkedList.head);
//        System.out.println(new MySingleLinkedList(reverse));
//        MySingleLinkedList mySingleLinkedList1 = new MySingleLinkedList();
//        mySingleLinkedList1.add(2);
//        mySingleLinkedList1.add(4);
        // 两个有序链表合并
//        MySingleLinkedList.Node mergeTwoLists =  MySingleLinkedList.mergeTwoLists(mySingleLinkedList.head,mySingleLinkedList1.head);
//        System.out.println(new MySingleLinkedList(mergeTwoLists));
        // 删除链表倒数n个节点
//        MySingleLinkedList.Node deleteLocateNode = MySingleLinkedList.deleteLocateNode(mySingleLinkedList.head, 3);
        // 删除链表倒数第n个节点
//        MySingleLinkedList.Node deleteLocateNode = MySingleLinkedList.deleteNode1(mySingleLinkedList.head, 3);
//        System.out.println(new MySingleLinkedList(deleteLocateNode));
        // 求链表的中间节点
        MySingleLinkedList.Node locateMiddle = MySingleLinkedList.locateMiddle(mySingleLinkedList.head);
        System.out.println(new MySingleLinkedList(locateMiddle));
    }


    public static class MySingleLinkedList {
        private Node head = null;

        public static Node locateMiddle(Node list) {
            Node fast = list, slow = list;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return new Node(slow.data);
        }

        public static Node deleteLocateNode(Node list, int n) {
            Node locateNode = MySingleLinkedList.locateNode(list, n);
            Node result = new Node(0, list);
            Node q = result;
            int i = 0;
            while (list != null) {
                if (list == locateNode) {
                    break;
                } else {
                    list = list.next;
                    i++;
                }
            }
            while (i > 0) {
                i--;
                q = q.next;
            }

            q.next = null;
            return result.next;
        }

        public static Node locateNode(Node list, int n) {
            Node fast = list, slow = list;
            while (n > 0) {
                fast = fast.next;
                n--;
            }
            while (fast != null && slow != null) {
                fast = fast.next;
                slow = slow.next;
            }
            return slow;
        }

        /**
         * 删除链表的第n个节点
         * @param list  链表
         * @param n     倒数第n
         * @return Node 返回删除后的新链表
         */
        public static Node deleteNode1(Node list, int n) {
            Node fast = list, slow = list,result=null;
            while (n > 0) {
                fast = fast.next;
                n--;
            }
            while (fast != null && slow != null) {
                fast = fast.next;
                result = slow;
                slow = slow.next;
            }
            if(result != null){
                result.next = result.next.next;
            }
            return list;
        }
        /**
         * 合并两个有序单链表
         *
         * @param l1
         * @param l2
         * @return
         */
        public static Node mergeTwoLists(Node l1, Node l2) {
            Node soldier = new Node(0); //利用哨兵结点简化实现难度 技巧三
            Node p = soldier;

            while (l1 != null && l2 != null) {
                if (l1.data < l2.data) {
                    p.next = l1;
                    l1 = l1.next;
                } else {
                    p.next = l2;
                    l2 = l2.next;
                }
                p = p.next;
            }
            // 检查末尾一个值的情况
            if (l1 != null) {
                p.next = l1;
            }
            if (l2 != null) {
                p.next = l2;
            }
            return soldier.next;
        }

        public MySingleLinkedList() {
        }

        public MySingleLinkedList(Node head) {
            this.head = head;
        }

        /**
         * 检测链表中是否有环
         *
         * @param list 单链表
         * @return boolean 是否有环
         */
        public static boolean checkCycle(Node list) {
            boolean result = false;
            if (list == null) {
                return result;
            }
            Node fast = list.next;
            Node slow = list;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (slow == fast) {
                    result = true;
                }
            }
            return result;
        }

        /**
         * 单链表反转
         *
         * @param list 单链表值
         * @return Node 单链表中的对象
         */
        public static Node reverse(Node list) {
            if (list == null) {
                return null;
            }
            Node curr = list, pre = null;
            while (curr != null) {
                // 将当前Node的Next值拿出来
                Node node = curr.next;
                // 将前面的值放在next里面
                curr.next = pre;
                // 将当前的值赋值给之前的值
                pre = curr;
                // 将下一次迭代的值进行传送
                curr = node;
            }
            return pre;
        }

        /**
         * 向单链表增加数据
         *
         * @param value 增加的值
         */
        public void add(int value) {
            Node newNode = new Node(value, null);
            //空链表，可以插入新节点作为head，也可以不操作
            if (head == null) {
                head = newNode;
            } else {
                Node q = head;
                while (q.next != null) {
                    q = q.next;
                }
                newNode.next = q.next;
                q.next = newNode;
            }
        }

        // 重写toString方法
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node q = head;
            sb.append(q.data).append(",");
            if(null == q.next){
                return sb.toString();
            }
            do {
                q = q.next;
                sb.append(q.data).append(",");
            } while (q.next != null);
            return sb.toString();

        }

        public static class Node {
            public Node(int data) {
                this.data = data;
            }

            public Node(int data, Node next) {
                this.data = data;
                this.next = next;
            }

            private Node next;
            private int data;

            public int getData() {
                return this.data;
            }

            public void setData(int data) {
                this.data = data;
            }

            public Node getNext() {
                return this.next;
            }

            public void setNext(Node next) {
                this.next = next;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "next=" + next +
                        ", data=" + data +
                        '}';
            }
        }

    }
}




