package chapter_one;

/**
 * Description: 二分查找 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 13:01:38
 */
public class BinarySearch {

    public static int KEY_NOT_FOUND = -1;

    /**
     * 在array中查找key的索引（数组必须有序）
     *
     * @param key   需要查找的数
     * @param array 一个有序的数组
     * @return key在array的索引
     */
    public static int rank(int key, int[] array) {
        int lo = 0, hi = array.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // lo       key <  mid      hi
            if (key < array[mid]) hi = mid - 1;
                // lo     mid < key     hi
            else if (key > array[mid]) lo = mid + 1;
            else return mid;
        }

        return KEY_NOT_FOUND;
    }
}
