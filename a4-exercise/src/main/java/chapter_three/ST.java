package chapter_three;

/**
 * 符号表
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/30 23:05
 */
public abstract class ST<Key, Value> {

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
     * 删除键对应的值,默认为延时删除
     */
    public Value delete(Key key) {
        return put(key, null);
    }

    /**
     * KEY是否在表中有对应的值，没有就返回null
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

    
}
