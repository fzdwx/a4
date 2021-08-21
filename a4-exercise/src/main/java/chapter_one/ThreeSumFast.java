package chapter_one;

import cn.hutool.core.collection.ListUtil;
import org.atomic.infrastructure.StopWatch;
import stdlib.In;
import util.DownLoadFileUtil;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/15 13:18
 */
public class ThreeSumFast {
    final static ExecutorService executorService = Executors.newFixedThreadPool(7);

    public static int count(int[] a) {

        Arrays.sort(a);

        final int size = a.length;
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                final int k = BinarySearch.rank(-a[i] - a[j], a);
                if (k > j) {
                    count++;
                }
            }
        }
        return count;
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

    private static void test(String url) {
        final String path = DownLoadFileUtil.download(url);

        final int[] array = In.readInts(path);

        System.out.println(url.substring(url.lastIndexOf("/") + 1) + " : [ " + count(array) + " ]");
    }
}
