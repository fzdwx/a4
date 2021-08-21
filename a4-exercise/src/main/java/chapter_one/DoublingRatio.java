package chapter_one;

import org.atomic.infrastructure.StopWatch;
import stdlib.StdRandom;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/15 13:56
 */
public class DoublingRatio {

    public static double timeTrial(int n) {
        int max = 1000000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-max, max);
        }
        final StopWatch stopWatch = StopWatch.of("test");

        ThreeSumFast.count(a);

        return stopWatch.elapsedTime();
    }

    public static void main(String[] args) {
        double time;
        double prev = timeTrial(1000);
        for (int n = 250; true; n += n) {
            time = timeTrial(n);
            System.out.printf("%6d %7.1f ", n, time);
            System.out.printf("%5.1f\n", time / prev);
            prev = time;
        }
    }
}
