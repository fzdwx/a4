package chapter_two;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.ReflectUtil;
import lombok.SneakyThrows;
import stdlib.StdRandom;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 各种排序算法的比较
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @module
 * @date 2021-08-22 09:51:09
 */
public class SortCompare {

    static List<Class<? extends Sortable>> sortClasses = ListUtil.of(
            Insertion.class
            , Selection.class
            , Merge.class,
            Shell.class
    );
    static CountDownLatch cdl;
    static ExecutorService pool;

    public static void randomArray(int n, int t) {
        Double[] a = new Double[n];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < n; j++) {
                a[j] = StdRandom.uniform();
            }
            time(sortClasses, a, i);
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        final int n = 10000;
        final int t = 5;
        cdl = new CountDownLatch(t);
        pool = Executors.newFixedThreadPool(t);
        randomArray(n, t);

        cdl.await();

        pool.shutdown();
    }

    @SneakyThrows
    private static void time(List<Class<? extends Sortable>> clazzList, Double[] a, int count) {
        final StopWatch watch = StopWatch.create("sort - " + count);
        pool.submit(() -> {
            clazzList.forEach((clazz) -> {
                watch.start(clazz.getSimpleName());
                final Sortable sortable = ReflectUtil.newInstance(clazz);
                sortable.sort(a);
                watch.stop();
            });
            System.out.println(watch.prettyPrint());
            cdl.countDown();
        });
    }
}
