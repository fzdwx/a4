package chapter_two.priorityQueue;

import java.util.Iterator;

/**
 * 使用无需数组实现的优先级队列，暂未考虑扩容
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/28 17:59
 */
public class ArrayMaxPQ<Key extends Comparable<Key>> implements MaxPQ<Key> {

    private Key[] keys;
    private int n = 0;

    public ArrayMaxPQ() {
        this(16);
    }

    public ArrayMaxPQ(int max) {
        this.keys = ( Key[] ) new Comparable[max];
    }

    public ArrayMaxPQ(Key[] k) {
        this.keys = k;
        this.n = k.length;
    }

    @Override
    public void insert(Key v) {
        keys[n++] = v;
    }

    @Override
    public Key max() {
        int max = getMaxIdx();
        return keys[max];
    }

    @Override
    public Key delMax() {
        int maxIdx = getMaxIdx();
        Key max = keys[maxIdx];
        MaxPQ.exchange(keys, --this.n, maxIdx);
        keys[this.n] = null;
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

    int getMaxIdx() {
        int max = 0;
        for (int j = 0; j < n; j++) {
            if (keys[j].compareTo(keys[max]) > 0) {
                max = j;
            }
        }
        return max;
    }
}
