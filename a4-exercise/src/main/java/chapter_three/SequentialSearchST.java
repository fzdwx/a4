package chapter_three;

import java.util.Iterator;

/**
 * 顺序搜索表
 * <p>
 * 基于无序链表,是效率最低的一种
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-09-01 18:49:05
 * @see ST
 */
public class SequentialSearchST<Key, Value> extends ST<Key, Value> {

    /** 链表首节点 */
    private Node<Key, Value> first;
    /** 当前搜索表的大小 */
    private int size = 0;

    /**
     * 将键值对存放到表中,如果存在就替换value,不存在就新建一个。
     *
     * @return 旧的value
     */
    @Override
    public Value put(Key key, Value value) {
        final Value temp;

        for (Node<Key, Value> x = first; x != null; x = x.next) {
            if (key.equals(x.key)) { // hit
                temp = x.val;
                x.val = value; // update
                return temp;
            }
        }

        this.first = Node.of(key, value, first); // new node
        this.size++;
        return null;
    }

    @Override
    public Value get(Key key) {
        for (Node<Key, Value> x = first; x != null; x = x.next) {
            if (key.equals(x.key)) return x.val;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        return new Iterable<Key>() {
            int tempSize = size;
            Node<Key, Value> tempFirst = first;
            Key tempKey;

            @Override
            public Iterator<Key> iterator() {
                return new Iterator<Key>() {
                    @Override
                    public boolean hasNext() {
                        return tempSize != 0;
                    }

                    @Override
                    public Key next() {
                        tempSize--;
                        tempKey = tempFirst.key;
                        tempFirst = tempFirst.next;
                        return tempKey;
                    }
                };
            }
        };
    }

    /**
     * 删除键对应的值,实现即时删除
     */
    @Override
    public Value delete(Key key) {
        final Value temp;

        Node<Key, Value> x = this.first;
        while (x != null) {
            if (x.next.key.equals(key)) { // hit
                temp = x.next.val;
                x.next = x.next.next; // delete
                this.size--;
                return temp;
            }
            x = x.next;
        }

        return null;
    }

    private static class Node<Key, Value> {
        Key key;
        Value val;
        Node<Key, Value> next;

        Node(Key key, Value val, Node<Key, Value> next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

        /**
         * 构造节点
         *
         * @return {@link Node}<{@link Key}, {@link Value}>
         */
        public static <Key, Value> Node<Key, Value> of(Key key, Value value, Node<Key, Value> next) {
            return new Node<Key, Value>(key, value, next);
        }

        @Override
        public String toString() {
            return "[" + key + " : " + val + "]";
        }
    }
}
