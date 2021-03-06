package chapter_two;

import chapter_two.sort.Heap;
import chapter_two.sort.Merge;
import chapter_two.sort.Quick;
import chapter_two.sort.QuickV2;
import chapter_two.sort.Sortable;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.SneakyThrows;
import org.atomic.infrastructure.StopWatch;
import stdlib.StdRandom;

import java.util.List;

/**
 * 各种排序算法的比较
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @module
 * @date 2021-08-22 09:51:09
 */
public class SortCompare {

    static List<Class<? extends Sortable>> sortClasses = ListUtil.of(
            Merge.class, Quick.class, QuickV2.class, Heap.class
    );

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
        final int n = 1000000;
        final int t = 5;
        randomArray(n, t);

    }

    @SneakyThrows
    private static void time(List<Class<? extends Sortable>> clazzList, Double[] a, int count) {
        final StopWatch watch = StopWatch.create("sort - " + count);
        clazzList.forEach((clazz) -> {
            watch.start(clazz.getSimpleName());
            final Sortable sortable = ReflectUtil.newInstance(clazz);
            sortable.sort(a);
            watch.stop();
        });
        System.out.println(watch.prettyPrint());
    }
}
