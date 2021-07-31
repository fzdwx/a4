package org.atomic.infrastructure.collection;

import java.util.Iterator;

/**
 * Description: 链 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 19:55:55
 * @see Iterable
 */
public abstract class Linked<Item> implements Iterable<Item> {

    /**
     * Description: 链表 迭代器 <br>
     *
     * @author <a href="mailto:likelovec@gmail.com">like</a>
     * @date 2021-07-31 19:58:52
     * @see Iterator
     */
    protected class LinkedIterator implements Iterator<Item> {
        private Node curr;

        /**
         * @param first 第一个节点
         */
        public LinkedIterator(Node first) {
            this.curr = first;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Item next() {
            final Item item = curr.item;
            curr = curr.next;
            return item;
        }
    }

    /**
     * Description: 链表 节点 <br>
     *
     * @author <a href="mailto:likelovec@gmail.com">like</a>
     * @date 2021-07-31 19:35:52
     */
    protected class Node {
        protected Item item;
        protected Node next;

        public Node(Item item) {
            this.item = item;
        }
    }
}
