package org.atomic.infrastructure.collection;

/**
 * Description: 栈 顶级接口 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 18:34:40
 */
public interface Stack<Item> {

    /**
     * 添加元素到head
     *
     * @param item 元素
     */
    void push(Item item);

    /**
     * 弹出最近添加的元素
     *
     * @return {@link String}
     */
    Item pop();

    /**
     * 当前栈是否为空
     *
     * @return boolean
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 返回当前栈元素的个数
     *
     * @return int
     */
    int size();
}
