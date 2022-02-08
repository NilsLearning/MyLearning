package indi.qun.learning.$01_arithmetic.part_02;

/**
 * 基于数组实现的顺序队列
 */
public class MyQueue {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(3);
        myQueue.enqueue("1");
        myQueue.enqueue("2");
        myQueue.enqueue("3");
        boolean b = myQueue.enqueue("5");
        System.out.println(b);
        System.out.println(myQueue.toString());
        String dequeue = myQueue.dequeue();
        System.out.println("出队元素:"+dequeue);
        System.out.println(myQueue.toString());
        myQueue.enqueue("4");
        System.out.println(myQueue.toString());
    }

    public String toString(){
        if(head == tail){
            return "队列结果为空";
        }
        StringBuilder str = new StringBuilder();
        for(int i=0;i<n;i++){
            str.append(items[i]).append(",");
        }
        return str.toString();
    }
    // 头指针
    int head = 0;
    // 尾指针
    int tail = 0;
    String[] items;
    // 数组大小
    int n = 0;
    public MyQueue(int capacity){
       items = new String[capacity];
       n = capacity;
    }

    /**
     * 入队操作
     * 入队需要考虑队列的重新排布
     * @param item 入队项
     * @return boolean 入队结果
     */
    public boolean enqueue(String item){
        // 如果tail等于n && head等于0,则入队结果为false;
        if(tail == n){
            if(head == 0){
                return  false;
            }
            // 进行队列数据复制
            for(int i = head;i<n;i++){
                items[i-head] = items[i];
            }
            tail=tail -head;
            head =0;
        }
        items[tail] = item;
        ++tail;
        return true;
    }

    /**
     * 出队操作
     * @return String 队列值
     */
    public String dequeue(){
        // 如果头指针和尾指针一样，则队列为空
        if(head == tail){
            return null;
        }
        String result = items[head];
        ++head;
        return result;
    }
}
