package chapter_two.priorityQueue;

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
    }

    @Override
    public void insert(Key v) {
        keys[n++] = v;
    }

    @Override
    public Key max() {
        return keys[getMaxIdx()];
    }

    @Override
    public Key delMax() {
        int i = this.n - 1;

        int max = getMaxIdx();
        MaxPQ.exchange(keys, i, max);
        keys[i] = null;

        this.n--;
        return keys[max];
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

        int iMax = this.n - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; i < this.n; i++) {
            b.append(keys[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
        return b.toString();
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
