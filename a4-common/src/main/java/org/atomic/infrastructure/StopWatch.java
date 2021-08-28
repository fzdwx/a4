package org.atomic.infrastructure;

import java.util.Arrays;

/**
 * 秒表
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @module
 * @date 2021-08-15 12:13:37
 */
public class StopWatch extends cn.hutool.core.date.StopWatch {

    private final long start;
    private final String taskName;

    private StopWatch(String taskName) {
        this.start = System.currentTimeMillis();
        this.taskName = taskName;
    }

    public static StopWatch of(String taskName) {
        return new StopWatch(taskName);
    }

    public static StopWatch create(String id) {
        return new StopWatch(id);
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

    // _________________________________________ 以下是定制hutool中的StopWatch

    /**
     * 打印出当前任务花费多少时间
     */
    public void log() {
        System.out.println(taskName + " : cost " + elapsedTime() + " s");
    }

    @Override
    public TaskInfo[] getTaskInfo() {
        final TaskInfo[] taskInfo = super.getTaskInfo();
        return Arrays.stream(taskInfo).sorted((t1, t2) -> {
            return Math.toIntExact(t1.getTimeNanos() - t2.getTimeNanos());
        }).toArray(TaskInfo[]::new);
    }
}
