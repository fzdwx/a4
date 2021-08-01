package org.atomic.infrastructure.collection;

import java.util.Iterator;

/**
 * Description: 链表 迭代器 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 19:58:52
 * @see java.util.Iterator
 */
class LinkedIterator<Item> implements Iterator<Item> {
    private AbstractNode<Item> curr;

    /**
     * @param first 第一个节点
     */
    public LinkedIterator(AbstractNode<Item> first) {
        this.curr = first;
    }

    @Override
    public boolean hasNext() {
        return curr != null;
    }

    @Override
    public Item next() {
        final Item item = curr.item();
        curr = curr.next();
        return item;
    }
}