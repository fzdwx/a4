package org.atomic.infrastructure;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: 一个简单的计数器 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 16:26:49
 */
public class Counter {

    /** 当前计数器的名字 */
    private final String name;
    /** 当前计数器累加次数 */
    private AtomicInteger count;

    // Initialization method start
    public Counter(String name) {
        this.name = name;
    }

    public static Counter of(String name) {
        return new Counter(name);
    }
    // Initialization method end

    /**
     * 使当前计算机加一
     *
     * @return {@link Counter}
     */
    public Counter increment() {
        count.incrementAndGet();

        return this;
    }

    /**
     * 返回当前计算机累加的次数
     *
     * @return int
     */
    public int tally() {
        return count.get();
    }


    @Override
    public String toString() {
        return count + " " + name;
    }
}
