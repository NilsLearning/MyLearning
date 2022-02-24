package indi.qun.learning.$01_arithmetic.part_02;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] ints = {1,3,5,7,11};
        // 查找的数字：5
        System.out.println(search(ints,7));

    }

    public static int search(int[] ints,int value){
        int high = ints.length-1;
        int low = 0;
        int middle = 0;

        while(low <= high){           // 循环退出条件，注意不是low<high
             middle = low+((high-low)>>1);   // mid=(low+high)/2,low和high比较大的话，有可能会导致溢出，最好使用low+((high-low)>>1)
            if(ints[middle]==value){
                return middle;
            }
            if(middle>value){
                high = middle-1; //注意这里的 +1 和 -1，如果直接写成 low=mid 或者 high=mid，就可能会发生死循环。比如，当 high=3，low=3 时，如果 a[3]不等于 value，就会导致一直循环不退出。
            }else{
                low = middle+1;
            }
        }
        if(ints[middle]==value){
            return middle;
        }else{
            return -1;
        }

    }


 }
