package org.atomic.infrastructure.collection;

/**
 * Description: 链表 节点 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 19:35:52
 */
abstract class AbstractNode<Item> {
    protected abstract AbstractNode<Item> next();

    protected abstract Item item();
}