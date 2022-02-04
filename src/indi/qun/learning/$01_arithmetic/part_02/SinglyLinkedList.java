package indi.qun.learning.$01_arithmetic.part_02;


/**
 * 学习目标:
 * 1.Java实现一个单链表，Java当中是没有单链表的
 * 2.单链表反转
 * 3.链表中环的检测  -1) 快慢指针  2)hashMap找重
 * 4.两个有序链表合并
 */
public class SinglyLinkedList {
    public static void main(String[] args) {
        var mySingleLinkedList = new MySingleLinkedList();
        mySingleLinkedList.add(1);
        mySingleLinkedList.add(2);
        mySingleLinkedList.add(3);
        System.out.println(mySingleLinkedList);
        MySingleLinkedList.Node reverse = MySingleLinkedList.reverse(mySingleLinkedList.head);
        System.out.println(new MySingleLinkedList(reverse));
    }

    public static class MySingleLinkedList {
        private Node head = null;

        public MySingleLinkedList() {
        }

        public MySingleLinkedList(Node head) {
            this.head = head;
        }

        public boolean checkCycle(Node list){
            boolean result = false;
            if(list == null){
                return result;
            }
            Node fast = list.next;
            Node slow = list;
            while(fast !=null && fast.next !=null){
                fast = fast.next.next;
                slow = slow.next;
                if(slow == fast){
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

        }

    }
}




