package chapter_one;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/15 13:02
 */
public class TowSumFast {

    public int count(int[] a) {
        final int size = a.length;
        int counter = 0;

        Arrays.sort(a);

        for (int i = 0; i < size; i++) {
            // 在数组中找到是a[i]的相反数
            if (BinarySearch.rank(-a[i], a) > i) {
                counter++;
            }
        }
        return counter;
    }

    public int[] twoSum(int[] nums, int target) {
        final int size = nums.length;
        List<Integer> numList = Arrays.stream(nums).boxed().collect(Collectors.toList());


        final int[] newArray = Arrays.copyOf(nums, size);
        Arrays.sort(newArray);
        for (int i = 0; i < size; i++) {
            final int j = Arrays.binarySearch(newArray, target - newArray[i]);
            if (j >= i) {
                return new int[]{numList.indexOf(newArray[i]), numList.lastIndexOf(newArray[j])};
            }
        }
        
        return null;
    }
}
