package org.atomic.infrastructure.collection;

/**
 * Description: 栈 顶级接口 <br>
 * <pre>
 *     先进后出
 * </pre>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 18:34:40
 */
public interface Stack<Item> extends Iterable<Item>, Collection<Item> {

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
}
