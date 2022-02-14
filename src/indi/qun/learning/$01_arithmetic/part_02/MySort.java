package indi.qun.learning.$01_arithmetic.part_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 记录下学习的排序算法代码
 */
public class MySort {
    public static void main(String[] args) {
        MySort mySort = new MySort();
        int[] numbers = new int[]{2, 1};
//        mySort.bubbleSort(numbers);
//        mySort.insertionSortFromWangZheng(numbers);
//        for (int i = 0; i < numbers.length; i++) {
//            System.out.println(numbers[i]);
//        }

        mySort.mergeSort(numbers);
        System.out.println(numbers);

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

    /**
     * 归并排序
     * @param numbers 数组
     */
    public void mergeSort(int[] numbers){
        mergeSortSon(numbers,0,numbers.length-1);
    }


    /**
     * 归并排序递归段
     * @param numbers
     */
    public void mergeSortSon(int[] numbers,int startIndex,int endIndex) {
        if (startIndex >= endIndex) return;
        int middle = (startIndex+endIndex)/2;
        mergeSortSon(numbers,startIndex,middle);
        mergeSortSon(numbers,middle+1,endIndex);
        merge(numbers, startIndex, middle, endIndex);
    }

    private  void merge(int[] a, int p, int q, int r) {
        int i = p;
        int j = q+1;
        int k = 0; // 初始化变量i, j, k
        int[] tmp = new int[r-p+1]; // 申请一个大小跟a[p...r]一样的临时数组
        while (i<=q && j<=r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++]; // i++等于i:=i+1
            } else {
                tmp[k++] = a[j++];
            }
        }

        // 判断哪个子数组中有剩余的数据
        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }

        // 将剩余的数据拷贝到临时数组tmp
        while (start <= end) {
            tmp[k++] = a[start++];
        }

        // 将tmp中的数组拷贝回a[p...r]
        for (i = 0; i <= r-p; ++i) {
            a[p+i] = tmp[i];
        }
    }

    /**
     * 快速排序
     * @param numbers
     */
    public void quickSort(int[] numbers){
        quickSortSon(numbers,0,numbers.length-1);
    }

    /**
     * 快速排序递归段
     * @param numbers    数组
     * @param startIndex 开始位置
     * @param endIndex   结束位置
     */
    public void quickSortSon(int[] numbers,int startIndex,int endIndex){
        if (startIndex >= endIndex) return;
        int partIndex = partion(numbers,startIndex,endIndex);
        quickSortSon(numbers,startIndex,partIndex-1);
        quickSortSon(numbers,partIndex+1,endIndex);
    }

    /**
     * 这个获得区分点的函数才是核心，其要解决最少三个问题：
     * 1.随机从数组中选取一个数，
     * 2.以选取的这个数为比较标准划分出小于这个数的左数组和大于这个数的右数组，
     * 3.统计出左数组的个数，得到划分点的下标，4.完成随机数下标的赋值操作。 快速排序不用进行合并操作，因为中间值的下标就是最终排序的下标，当递归完成时，就已经完成了排序！
     * @param numbers
     * @param startIndex
     * @param endIndex
     * @return
     */
    private int partion(int[] numbers, int startIndex, int endIndex) {
        int partion = 0;
        int left = 0;
        int right = endIndex;
        for(int i=0;i<=endIndex;i++){
            if(numbers[i]>numbers[0]){

            }
            if(numbers[endIndex-i]>numbers[0]){

            }
        }
        return partion;
    }


}
