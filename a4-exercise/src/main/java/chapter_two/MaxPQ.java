package chapter_two;

/**
 * 优先级队列
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/28 17:47
 */
public interface MaxPQ<Key extends Comparable<Key>> {

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
