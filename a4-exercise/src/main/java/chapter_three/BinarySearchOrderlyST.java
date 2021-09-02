package chapter_three;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * 基于二分查找的顺序表，基于有序数组
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/9/2 18:05
 */
public class BinarySearchOrderlyST<Key extends Comparable<Key>, Value> extends OrderlyST<Key, Value> {

    private Key[] keys;
    private Value[] values;
    private int size;

    public BinarySearchOrderlyST(int capacity) {
        keys = ( Key[] ) new Comparable[capacity];
        values = ( Value[] ) new Comparable[capacity];
    }

    @Override
    public Value put(Key key, Value value) {
        checkKeyIsNotNull(key);
        final Value temp;

        int i = rank(key);
        if (hasKey(key, i)) {  // hit
            temp = values[i];
            values[i] = value; // update
            return temp;
        }

        if (size == keys.length) resize(2 * keys.length);
        for (int j = size; j > i; j--) { // [index...size] move right 1 step
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        this.size++;
        return null;
    }

    @Override
    public Value get(Key key) {
        checkKeyIsNotNull(key);
        if (isEmpty()) return null;

        int i = rank(key);
        if (hasKey(key, i))
            return values[i];
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        return Arrays.asList(keys);
    }

    @Override
    public Value delete(Key key) {
        checkKeyIsNotNull(key);
        if (isEmpty()) return null;

        int i = rank(key);
        if (!hasKey(key, i)) {
            return null;
        }

        final Value temp;
        for (int j = i; j < size - 1; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }

        size--;
        keys[size] = null;  // to avoid loitering
        values[size] = null;
        temp = values[size];

        // resize if 1/4 full
        if (size > 0 && size == keys.length / 4) resize(keys.length / 2);


        return temp;
    }

    @Override
    public Key min() {
        return keys[0];
    }

    @Override
    public Key max() {
        return keys[size - 1];
    }

    @Override
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        int i = rank(key);
        if (hasKey(key, i)) return keys[i];
        if (i == 0) throw new NoSuchElementException("argument to floor() is too small");
        else return keys[i - 1];
    }

    @Override
    public Key ceiling(Key key) {
        return keys[rank(key)];
    }

    @Override
    public int rank(Key key) {
        checkKeyIsNotNull(key);

        int lo = 0, hi = size - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    @Override
    public Key select(int no) {
        return keys[no];
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new LinkedList<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.offer(keys[i]);
        }
        if (contains(hi)) {
            q.offer(keys[rank(hi)]);
        }
        return q;
    }

    public Value deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        return delete(min());
    }

    public Value deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        return delete(max());
    }

    // resize the underlying arrays
    private void resize(int capacity) {
        assert capacity >= size;
        Key[] tempk = ( Key[] ) new Comparable[capacity];
        Value[] tempv = ( Value[] ) new Object[capacity];
        for (int i = 0; i < size; i++) {
            tempk[i] = keys[i];
            tempv[i] = values[i];
        }
        values = tempv;
        keys = tempk;
    }

    /**
     * key exist ?
     *
     * @param key   key
     * @param index 索引
     */
    private boolean hasKey(Key key, int index) {
        return index < size && keys[index].compareTo(key) == 0;
    }
}
