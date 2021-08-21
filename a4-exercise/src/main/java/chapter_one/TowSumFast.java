package chapter_one;

import cn.hutool.core.collection.ListUtil;
import org.atomic.infrastructure.StopWatch;
import stdlib.In;
import util.DownLoadFileUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/15 13:02
 */
public class TowSumFast {

    final static ExecutorService executorService = Executors.newFixedThreadPool(7);

    public static int twoSum2(int[] nums, int target) {
        int counter = 0;
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                counter++;
            }
            hashtable.put(nums[i], i);
        }
        return counter;
    }

    public static void main(String[] args) {
        final List<String> urls = ListUtil.of(
                "https://algs4.cs.princeton.edu/14analysis/1Kints.txt"
                ,
                "https://algs4.cs.princeton.edu/14analysis/2Kints.txt",
                "https://algs4.cs.princeton.edu/14analysis/4Kints.txt",
                "https://algs4.cs.princeton.edu/14analysis/8Kints.txt",
                "https://algs4.cs.princeton.edu/14analysis/16Kints.txt",
                "https://algs4.cs.princeton.edu/14analysis/32Kints.txt",
                "https://algs4.cs.princeton.edu/14analysis/1Mints.txt"
        );

        urls.forEach((url) -> {
            executorService.submit(() -> {
                final StopWatch stopWatch = StopWatch.of(url.substring(url.lastIndexOf("/") + 1));
                test(url);
                stopWatch.log();
            });
        });
        executorService.shutdown();
    }

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

        Arrays.sort(nums);
        for (int i = 0; i < size; i++) {
            final int j = Arrays.binarySearch(nums, target - nums[i]);
            if (j >= i) {
                return new int[]{numList.indexOf(nums[i]), numList.lastIndexOf(nums[j])};
            }
        }

        return null;
    }

    private static void test(String url) {
        final String path = DownLoadFileUtil.download(url);

        final int[] array = In.readInts(path);

        System.out.println(url.substring(url.lastIndexOf("/") + 1) + " : [ " + twoSum2(array, 0) + " ]");
    }
}
