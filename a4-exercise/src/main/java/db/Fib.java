package db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 斐波那契数列
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @module
 * @date 2021-08-25 09:24:47
 */
public class Fib {

    /**
     * 使用最简单的递归实现
     */
    static int f1(int n) {
        if (n == 0 || n == 1) return n;
        return f1(n - 1) + f1(n - 2);
    }

    /**
     * 使用递归+缓存实现
     */
    static int f2(int n) {
        int[] cache = new int[n + 1];

        // 进行带缓存的递归
        return f2(cache, n);
    }

    static int f2(int[] cache, int n) {
        if (n == 0 || n == 1) return n;

        // 如果没有计算就进行计算
        if (cache[n] == 0)
            cache[n] = f2(cache, n - 2) + f2(cache, n - 1);

        return cache[n];
    }

    /**
     * dp数组迭代
     */
    static int f3(int n) {
        if (n == 0) return n;

        int[] dp = new int[n + 1];
        // base case
        dp[0] = 0;
        dp[1] = 1;

        // 状态转移
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


    /**
     * 使用状态压缩
     */
    static int f4(int n) {
        if (n <= 0) return 0;
        if (n == 2 || n == 1) {return 1;}

        int prev = 1, curr = 1;

        for (int i = 3; i <= n; i++) {
            int sum = prev + curr;

            prev = curr;
            curr = sum;
        }

        return curr;
    }
}


class FibTest {

    int n;

    @BeforeEach
    void setUp() {
        n = 10;
    }

    @Test
    @DisplayName("最简单的求最值")
    void test_f1() {
        final int fib = Fib.f1(n);
        System.out.println(fib);
    }

    @Test
    @DisplayName("使用备忘录，来存放以前求过的值")
    void test_f2() {
        final int fib = Fib.f2(n);
        System.out.println(fib);
    }

    @Test
    @DisplayName("使用动态规划迭代")
    void test_f3() {
        final int fib = Fib.f3(n);
        System.out.println(fib);
    }

    @Test
    @DisplayName("状态压缩")
    void test_f4() {
        final int fib = Fib.f4(n);
        System.out.println(fib);
    }

}
