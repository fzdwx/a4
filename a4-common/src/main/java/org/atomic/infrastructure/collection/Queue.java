package org.atomic.infrastructure.collection;

/**
 * Description: 队列 <br>
 * <pre>
 *     先进先出
 * </pre>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 20:02:36
 */
public interface Queue<Item> extends Collection<Item> {

    /**
     * 入队，到队列中的最后一个
     *
     * @param item 元素
     */
    public void enqueue(Item item);

    /**
     * 出列
     *
     * @return {@link Item}
     */
    public Item dequeue();
}
