package chapter_one;

import cn.hutool.core.collection.ListUtil;
import org.atomic.infrastructure.StopWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import stdlib.In;
import util.DownLoadFileUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/15 13:13
 */
class ThreeSumTest {
    static List<String> urls;
    ExecutorService executorService = Executors.newFixedThreadPool(7);
    ThreeSum threeSum = new ThreeSum();

    private static void test(String url) {
        final String path = DownLoadFileUtil.download(url);

        final int[] array = In.readInts(path);

        System.out.println(url.substring(url.lastIndexOf("/") + 1) + " : [ " + ThreeSum.count(array) + " ]");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        urls = ListUtil.of(
                "https://algs4.cs.princeton.edu/14analysis/1Kints.txt",
                "https://algs4.cs.princeton.edu/14analysis/2Kints.txt",
                "https://algs4.cs.princeton.edu/14analysis/4Kints.txt",
                "https://algs4.cs.princeton.edu/14analysis/8Kints.txt",
                "https://algs4.cs.princeton.edu/14analysis/16Kints.txt",
                "https://algs4.cs.princeton.edu/14analysis/32Kints.txt",
                "https://algs4.cs.princeton.edu/14analysis/1Mints.txt"
        );
    }

    @Test
    void testCount() {
        urls.forEach((url) -> {
            executorService.submit(() -> {
                final StopWatch stopWatch = StopWatch.of(url.substring(url.lastIndexOf("/") + 1));
                test(url);
                stopWatch.log();
            });
        });
        // executorService.shutdown();
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme