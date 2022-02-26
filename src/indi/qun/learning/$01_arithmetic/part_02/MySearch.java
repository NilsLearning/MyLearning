package indi.qun.learning.$01_arithmetic.part_02;

/**
 * 记录下学习
 */
public class MySearch {

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 3, 4, 5, 6};
        int firstAppearByBinarySearch = findFirstAppearByBinarySearch(ints, ints.length, 3);
        System.out.println("firstAppearByBinarySearch--" + firstAppearByBinarySearch);
        int lastAppearByBinarySearch = findLastAppearByBinarySearch(ints, ints.length, 3);
        System.out.println("lastAppearByBinarySearch--" + lastAppearByBinarySearch);
        int firstGreaterThanByBinarySearch = findFirstGreaterThanByBinarySearch(ints, ints.length, 3);
        System.out.println("firstGreaterThanByBinarySearch--" + firstGreaterThanByBinarySearch);
        int lastLessThanByBinarySearch = findLastLessThanByBinarySearch(ints, ints.length, 2);
        System.out.println("lastLessThanByBinarySearch--"+lastLessThanByBinarySearch);
    }

    /**
     * 查找第一个值等于给定值的元素
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int findFirstAppearByBinarySearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (a[mid - 1] != value)) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    // 如果我们求解的是第一个值等于给定值的元素，当 a[mid]等于要查找的值时，我们就需要确认一下这个 a[mid]是不是第一个值等于给定值的元素。
    // 我们重点看【 ((mid == 0) || (a[mid - 1] != value)) return mid 】代码。如果 mid 等于 0，那这个元素已经是数组的第一个元素，
    // 那它肯定是我们要找的；如果 mid 不等于 0，
    // 但 a[mid]的前一个元素 a[mid-1]不等于 value，那也说明 a[mid]就是我们要找的第一个值等于给定值的元素。
    // 如果经过检查之后发现 a[mid]前面的一个元素 a[mid-1]也等于 value，那说明此时的 a[mid]肯定不是我们要查找的第一个值等于给定值的元素。
    // 那我们就更新 high=mid-1，因为要找的元素肯定出现在[low, mid-1]之间。

    /**
     * 查找值等于给定值的元素
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int findLastAppearByBinarySearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] != value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int findFirstGreaterThanByBinarySearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if (mid == 0 || a[mid - 1] < value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else if (a[mid] < value) {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int findLastLessThanByBinarySearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] <= value) {
                if (mid == n || a[mid + 1] > value) {
                    return mid;
                } else {
                    low = mid + 1;
                }

            }
        }
        return -1;
    }
}
