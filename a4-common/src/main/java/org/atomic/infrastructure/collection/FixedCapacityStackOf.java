package org.atomic.infrastructure.collection;

/**
 * Description: 固定容量的栈 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 18:21:47
 */
public class FixedCapacityStackOf<Item> {

    private int n;
    private Item[] a;

    // Initialization method start
    public FixedCapacityStackOf(int capacity) {
        this.n = capacity;
        this.a = ( Item[] ) new Object[n];
    }

    public FixedCapacityStackOf<Item> of(int capacity) {
        return new FixedCapacityStackOf<>(capacity);
    }
    // Initialization method start

    /**
     * 添加元素到head
     *
     * @param item 元素
     */
    public void push(Item item) {
        a[n++] = item;
    }

    /**
     * 弹出最近添加的元素
     *
     * @return {@link String}
     */
    public Item pop() {
        return a[--n];
    }

    /**
     * 当前栈是否为空
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 返回当前栈元素的个数
     *
     * @return int
     */
    public int size() {
        return n;
    }

}
