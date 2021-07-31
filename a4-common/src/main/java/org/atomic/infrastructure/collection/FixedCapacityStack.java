package org.atomic.infrastructure.collection;

/**
 * Description: 固定容量的栈 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 18:21:47
 */
public class FixedCapacityStack<Item> implements Stack<Item> {

    protected int n;
    protected Item[] a;

    // Initialization method start
    public FixedCapacityStack(int capacity) {
        this.n = capacity;
        this.a = ( Item[] ) new Object[n];
    }

    public FixedCapacityStack<Item> of(int capacity) {
        return new FixedCapacityStack<>(capacity);
    }
    // Initialization method start

    @Override
    public void push(Item item) {
        a[n++] = item;
    }

    @Override
    public Item pop() {
        return a[--n];
    }

    @Override
    public int size() {
        return n;
    }
}
