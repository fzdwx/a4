package org.atomic.infrastructure;

import stdlib.StdDraw;
/**
 * Description: 可视化的数据累加器 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 16:40:10
 * @see Accumulator
 */
public class VisualAccumulator extends Accumulator {

    public VisualAccumulator(int trials, double max) {
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
    }

    @Override
    public void addDataValue(double val) {
        super.addDataValue(val);

        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, this.mean());
    }
}
