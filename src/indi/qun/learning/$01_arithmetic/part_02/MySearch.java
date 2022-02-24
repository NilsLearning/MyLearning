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
