package indi.qun.learning.$01_arithmetic.part_02;

/**
 * 循环队列
 */
public class CircularQueue {

    int head = 0;
    int tail = 0;
    int n = 0;
    String[] items;

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(4);
        circularQueue.enqueue("1");
        circularQueue.enqueue("2");
        System.out.println(circularQueue.enqueue("3"));
        System.out.println(circularQueue.enqueue("4"));
        System.out.println(circularQueue.toString());
        System.out.println(circularQueue.dequeue());
        circularQueue.printAll();



    }

    public CircularQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    public String toString() {
        if (head == tail) {
            return "队列结果为空";
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            str.append(items[i]).append(",");
        }
        return str.toString();
    }

//    /**
//     * 入队操作
//     *
//     * @param item 项目
//     * @return
//     */
//    public boolean enqueue(String item) {
//        // 队列已满
//        if ((tail + 1) % n == head) {
//            return false;
//        }
//        items[tail] = item;
//        tail = (tail + 1) % n;
//        return true;
//    }
//
//    /**
//     * 出队操作
//     *
//     * @return
//     */
//    public String dequeue() {
//        if (head == tail) return null;
//        String result = items[head];
//        head = (head + 1) % n;
//        return result;
//    }

    // 入队
    public boolean enqueue(String item) {
        // 队列满了
        if ((tail + 1) % n == head) return false;
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    // 出队
    public String dequeue() {
        // 如果head == tail 表示队列为空
        if (head == tail) return null;
        String ret = items[head];
        head = (head + 1) % n;
        return ret;
    }

    public void printAll() {
        if (0 == n) return;
        for (int i = head; i % n != tail; i = (i + 1) % n) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }
}
