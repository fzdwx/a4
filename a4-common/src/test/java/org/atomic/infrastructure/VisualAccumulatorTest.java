package org.atomic.infrastructure;

import org.junit.jupiter.api.Test;
import stdlib.StdRandom;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/7/31 16:44
 */
class VisualAccumulatorTest {

    @Test
    void f1() {
        final int trials = 2000;
        final VisualAccumulator a = new VisualAccumulator(trials, 1.0);

        for (int i = 0; i < trials; i++) {
            a.addDataValue(StdRandom.uniform());
        }
        System.out.println(a);
    }
}