package org.atomic.infrastructure;

/**
 * 秒表
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @module
 * @date 2021-08-15 12:13:37
 */
public class StopWatch {

    private final long start;
    private final String taskName;

    private StopWatch(String taskName) {
        this.start = System.currentTimeMillis();
        this.taskName = taskName;
    }

    public static StopWatch of(String taskName) {
        return new StopWatch(taskName);
    }

    /**
     * 运行时间
     *
     * @return 单位 s
     */
    public double elapsedTime() {
        long cost = System.currentTimeMillis() - start;
        return cost / 1000.0;
    }

    /**
     * 打印出当前任务花费多少时间
     */
    public void log() {
        System.out.println(taskName + " : cost " + elapsedTime() + " s");
    }

}
