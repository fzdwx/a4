package chapter_one;

/**
 * Description: 混乱的 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 13:00:09
 */
public class Messy {

    /**
     * 计算两个非负整数 p 和 q 的最大公约数。
     *
     * <pre>
     *  若q是0，则最大公约数为p。
     *  否则，将p除以q得到余数r，
     *  p和q的最大公约数就是q和r的最大公约数
     * </pre>
     *
     * @param p 第一个数
     * @param q 第二个数
     * @return p 和 q 的最大公约数
     */
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }
}
