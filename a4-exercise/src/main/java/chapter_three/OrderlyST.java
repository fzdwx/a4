package chapter_three;

/**
 * 有序的符号表
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/31 18:31
 */
public abstract class OrderlyST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {

    /**
     * 将键值对存放到表中，如果value为空，则删除KEY
     */
    public abstract Value put(Key key, Value value);

    /**
     * 获取键对应的值，如果不存在则返回null
     */
    public abstract Value get(Key key);

    /**
     * 表中键值对的数量
     */
    public abstract int size();

    /**
     * 返回所有键
     */
    public abstract Iterable<Key> keys();

    /**
     * 删除键对应的值
     * 默认为延时删除
     */
    public Value delete(Key key) {
        return put(key, null);
    }

    /**
     * KEY是否在表中就对应的值
     */
    public boolean contains(Key key) {
        return get(key) == null;
    }

    /**
     * 表是否为空
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 最小的键
     */
    public abstract Key min();

    /**
     * 最大的键
     */
    public abstract Key max();

    /**
     * 小于等于key的最大键
     */
    public abstract Key floor(Key key);

    /**
     * 大于等于key的最小键
     */
    public abstract Key ceiling(Key key);

    /**
     * 小于key的键的数量
     */
    public abstract int rank(Key key);

    /**
     * 排名为no的键
     */
    public abstract Key select(int no);

    /**
     * [lo..hi]之间的所有键，排序好的
     */
    public abstract Iterable<Key> keys(Key lo, Key hi);

    /**
     * 表中的所有键，排序好的
     */
    public Iterable<Key> Keys() {
        return keys(min(), max());
    }

    /**
     * [lo..hi]之间的键的数量
     */
    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0)
            return 0;
        else if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    /**
     * 删除最小的键值对
     */
    public Value deleteMin() {
        return delete(min());
    }

    /**
     * 删除最大的键值对
     */
    public Value deleteMax() {
        return delete(max());
    }
}
