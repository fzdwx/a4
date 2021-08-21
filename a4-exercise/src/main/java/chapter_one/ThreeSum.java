package chapter_one;

import cn.hutool.core.collection.ListUtil;
import org.atomic.infrastructure.Accumulator;
import org.atomic.infrastructure.StopWatch;
import stdlib.In;
import util.DownLoadFileUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 计算给定的数组中，三个连续的数之和为0的次数
 * Data files:
 * "https://algs4.cs.princeton.edu/14analysis/1Kints.txt",
 * "https://algs4.cs.princeton.edu/14analysis/2Kints.txt",
 * "https://algs4.cs.princeton.edu/14analysis/4Kints.txt",
 * "https://algs4.cs.princeton.edu/14analysis/8Kints.txt",
 * "https://algs4.cs.princeton.edu/14analysis/16Kints.txt",
 * "https://algs4.cs.princeton.edu/14analysis/32Kints.txt",
 * "https://algs4.cs.princeton.edu/14analysis/1Mints.txt",
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @module
 * @date 2021-08-15 12:06:39
 */
public class ThreeSum {

    final static ExecutorService executorService = Executors.newFixedThreadPool(7);

    public static int count(int[] array) {
        final int size = array.length;
        final Accumulator a = Accumulator.of();
        // a[i]+ a[j] = target
        // a[j] = target - a[i]
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    if (array[i] + array[j] + array[k] == 0) {
                        a.addDataValue(1);
                    }
                }
            }
        }
        return (( int ) (a.getDataValue()));
    }

    public static void main(String[] args) {

        final List<String> urls = ListUtil.of(
                "https://algs4.cs.princeton.edu/14analysis/1Kints.txt",
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

    private static void test(String url) {
        final String path = DownLoadFileUtil.download(url);

        final int[] array = In.readInts(path);

        System.out.println(url.substring(url.lastIndexOf("/") + 1) + " : [ " + count(array) + " ]");
    }
}
