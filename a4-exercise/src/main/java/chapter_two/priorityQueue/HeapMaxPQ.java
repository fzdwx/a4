package chapter_two.priorityQueue;

import java.util.Iterator;

/**
 * 二叉堆实现
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/29 17:03
 */
public class HeapMaxPQ<Key extends Comparable<Key>> implements MaxPQ<Key>, Iterable<Key> {

    // 基于堆的完全二叉树
    private Key[] keys;
    // 存储于keys[1..N]中，keys[0]不使用
    private int n = 0;

    public HeapMaxPQ(int n) {
        this.keys = ( Key[] ) new Comparable[n + 1];
    }

    public HeapMaxPQ(Key[] keys) {
        this.keys = ( Key[] ) new Comparable[keys.length + 1];
        for (Key key : keys) {
            insert(key);
        }
    }

    @Override
    public void insert(Key v) {
        keys[++n] = v;
        swim(n);
    }

    @Override
    public Key max() {
        return keys[1];
    }

    @Override
    public Key delMax() {
        Key max = keys[1];
        MaxPQ.exchange(keys, 1, n--);
        keys[n + 1] = null;
        sink(1);

        return max;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public String toString() {
        if (keys == null)
            return "null";

        int iMax = n - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; i <= n; i++) {
            b.append(keys[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
        return b.toString();
    }

    @Override
    public Iterator<Key> iterator() {
        return new Iterator<Key>() {
            @Override
            public boolean hasNext() {
                return !isEmpty();
            }

            @Override
            public Key next() {
                return delMax();
            }
        };
    }

    /**
     * 将k处的节点上浮
     * <p>
     * 如果堆的有序状态因为某个节点变得比它的父节点大，那就可以通过交换它和它的父节点来修复。
     * <pre>
     *     1.判断父节点 k/2 是否小于子节点 k
     *     2.父节点小于子节点就交换两个节点的位置
     *     3.继续判断k节点的节点直到根节点
     * </pre>
     */
    void swim(int k) {
        while (k > 1 && MaxPQ.less(keys, k / 2, k)) {
            MaxPQ.exchange(keys, k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 将k处的节点下沉
     * <p>
     * 如果堆的有序状态因为某个节点变得比它的两个子节点或其中之一小而被打破，那么就可以让它和子节点中较大一个来交换进行修复。
     * <pre>
     *     1.找到子节点中那个大的节点
     *     2.k处的节点大于子节点，就说明不用下沉
     *     3.否则就交换父节点k和子节点2k
     * </pre>
     */
    void sink(int k) {
        int son;
        while (2 * k <= n) {
            son = 2 * k;
            if (son < n && MaxPQ.less(keys, son, son + 1)) son++;
            if (!MaxPQ.less(keys, k, son)) break;
            MaxPQ.exchange(keys, k, son);
            k = son;
        }
    }
}
