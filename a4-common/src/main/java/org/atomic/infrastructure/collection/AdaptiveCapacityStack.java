package org.atomic.infrastructure.collection;

/**
 * Description:  自适应容量堆栈 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 18:46:23
 * @see FixedCapacityStack
 */
public class AdaptiveCapacityStack<Item> extends FixedCapacityStack<Item> {

    protected int n;
    protected Item[] a;

    // Initialization method start
    public AdaptiveCapacityStack(int capacity) {
        super(capacity);
    }

    public AdaptiveCapacityStack<Item> of(int capacity) {
        return new AdaptiveCapacityStack<>(capacity);
    }
    // Initialization method end

    /**
     * 扩容 / 缩容
     *
     * @param maxCapacity 最大容量
     */
    private void resize(int maxCapacity) {
        Item[] temp = ( Item[] ) new Object[maxCapacity];
        if (n >= 0)
            System.arraycopy(a, 0, temp, 0, n);
        a = temp;
    }

    @Override
    public void push(Item item) {
        if (n == a.length) resize(a.length * 2);
        super.push(item);
    }

    @Override
    public Item pop() {
        final Item item = super.pop();
        a[n] = null; // 移除在数组中的item，避免对象游离。
        if (n > 0 && n == a.length / 4) resize(a.length / 2);
        return item;
    }
}
