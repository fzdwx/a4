package chapter_two.priorityQueue;

/**
 * 优先级队列
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/28 17:47
 */
public interface MaxPQ<Key extends Comparable<Key>> {

    /** exchange a[i] and a[j] */
    static void exchange(Comparable[] a, int i, int j) {
        final Comparable<? extends Object> temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //  keys[i] <  keys[j] ?
    static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    // 插入一个元素
    void insert(Key v);

    // 返回最大元素
    Key max();

    // 删除最大元素
    Key delMax();

    // 当前队列是否为空
    boolean isEmpty();

    // 返回当前队列元素个数
    int size();
}
