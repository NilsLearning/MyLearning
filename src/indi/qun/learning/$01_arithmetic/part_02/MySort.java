package indi.qun.learning.$01_arithmetic.part_02;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 记录下学习的排序算法代码
 */
public class MySort {
    public static void main(String[] args) {
        MySort mySort = new MySort();
        int[] numbers = new int[]{2, 1};
        mySort.bubbleSort(numbers);
        mySort.insertionSortFromWangZheng(numbers);
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
        List list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        Collections.sort(list);
        Arrays.sort(numbers);
    }

    /**
     * 插入排序
     *
     * @param numbers
     */
    public void insertionSort(int[] numbers) {
        int n = numbers.length;
        if (n <= 1) return;

        for (int i = 1; i < n; ++i) {
            for (int j = i; j > 0 && (numbers[j] > numbers[j - 1]); --j) {
                swap(numbers,j,j-1);
            }
        }
    }


    /**
     * 插入排序，a表示数组，来自王铮的算法，但是目前没有领悟出来他这种优化的写法，所以自己还是采用老办法写了一个
     *
     * @param numbers
     */
    public void insertionSortFromWangZheng(int[] numbers) {
        int n = numbers.length;
        if (n <= 1) return;

        for (int i = 1; i < n; ++i) {
            int value = numbers[i];
            int j = i - 1;
            // 查找插入的位置
            while (j>=0 && numbers[j] > value){
                numbers[j + 1] = numbers[j];
                j--;
            }

//            for (; j >= 0; j--) {
//                if (numbers[j] > value) {
//                    numbers[j + 1] = numbers[j];  // 数据移动
//                } else {
//                    break;
//                }
//            }
            numbers[j + 1] = value; // 插入数据
        }
    }

    /**
     * 数据交换
     * @param numbers  数组
     * @param i
     * @param j
     */
    public void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;

    }

    /**
     * 冒泡排序，于我而言，冒泡排序的精髓在于取了中间值进行数据交换
     *
     * @param numbers 数组
     */
    public void bubbleSort(int[] numbers) {
        int n = numbers.length;
        if (n == 0) return;

        for (int i = 0; i < n; i++) { //外层循环,控制从第几个数开始拿
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) { // 内层循环，拿出数组里面所有的值进行比较
                // 元素按照从小到大的顺序排列
//                if(numbers[j]>numbers[j+1]){
//                交换位置
//                  swap(numbers,j,j+1)
//                }
                // 元素按照从大到小的顺序进行排列
                if (numbers[j] < numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;  // 没有数据交换，提前退出
        }
    }





}
