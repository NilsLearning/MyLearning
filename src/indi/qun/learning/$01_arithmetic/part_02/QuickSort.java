package indi.qun.learning.$01_arithmetic.part_02;

public class QuickSort {

    public static void main(String[] args) {
        int[] ints = {1,5,4,3,2};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(ints);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    /**
     * 快速排序
     *
     * @param numbers
     */
    public void quickSort(int[] numbers) {
        quickSortSon(numbers, 0, numbers.length - 1);
    }

    /**
     * 快速排序递归段
     *
     * @param numbers    数组
     * @param startIndex 开始位置
     * @param endIndex   结束位置
     */
    public void quickSortSon(int[] numbers, int startIndex, int endIndex) {
        if (startIndex >= endIndex) return;
        int partIndex = partition(numbers, startIndex, endIndex);
        quickSortSon(numbers, startIndex, partIndex - 1);
        quickSortSon(numbers, partIndex + 1, endIndex);
    }

    /**
     * 这个获得区分点的函数才是核心，其要解决最少三个问题：
     * 1.随机从数组中选取一个数，
     * 2.以选取的这个数为比较标准划分出小于这个数的左数组和大于这个数的右数组，
     * 3.统计出左数组的个数，得到划分点的下标，4.完成随机数下标的赋值操作。 快速排序不用进行合并操作，因为中间值的下标就是最终排序的下标，当递归完成时，就已经完成了排序！
     *
     * @param numbers
     * @param startIndex
     * @param endIndex
     * @return
     */
//    private int partition(int[] numbers, int startIndex, int endIndex) {
//        int partion = 0;
//        int left = 0;
//        int right = 0;
//        for (int i = 0; i <= endIndex; i++) {
//            if (numbers[i] > numbers[startIndex]) {
//                left = numbers[i];
//                numbers[endIndex - i] = left;
//            }
//            if (numbers[endIndex - i] > numbers[startIndex]) {
//                right = numbers[endIndex - i];
//                numbers[i] = right;
//            }
//        }
//        return partion;
//    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for(int j = p; j < r; ++j) {
            if (a[j] < pivot) {
                if (i == j) {
                    ++i;
                } else {
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }

        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;

        System.out.println("i=" + i);
        return i;
    }
}
