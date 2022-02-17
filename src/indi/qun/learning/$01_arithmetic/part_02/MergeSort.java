package indi.qun.learning.$01_arithmetic.part_02;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        MergeSort mySort = new MergeSort();
        int[] numbers = new int[]{2, 1};
        mySort.mergeSort(numbers);
        System.out.println(numbers);
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

}
