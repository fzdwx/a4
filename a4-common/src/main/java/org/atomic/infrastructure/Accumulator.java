package org.atomic.infrastructure;

/**
 * Description: 累加数据的累加器 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 16:35:02
 */
public class Accumulator {

    protected double total;
    protected int N;

    // Initialization method start
    public Accumulator() {
    }

    public static Accumulator of() {
        return new Accumulator();
    }
    // Initialization method end

    /**
     * 添加一个新的数据值
     *
     * @param val 瓦尔
     */
    public void addDataValue(double val) {
        total += val;
        N++;
    }

    /**
     * 所有数据的平均值
     *
     * @return double
     */
    public double mean() {
        return total / N;
    }

    @Override
    public String toString() {
        return "Mean (" + N + " values): " + String.format("%7.5f", mean());
    }
}
