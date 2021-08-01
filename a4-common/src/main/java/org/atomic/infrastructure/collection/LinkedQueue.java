package org.atomic.infrastructure.collection;

import java.util.Iterator;

/**
 * Description: 链表实现的队列 <br>
 *
 * <pre>
 *     先进先出
 * </pre>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 19:47:23
 * @see Iterable
 */
public class LinkedQueue<Item> extends Linked<Item> implements Queue<Item> {

    /** 头节点 */
    private Node head;
    /** 尾节点 */
    private Node tail;
    /** 元素个数 */
    private int n;

    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(head);
    }

    @Override
    public void enqueue(Item item) {
        final Node oldTail = this.tail;
        tail = new Node(item);
        tail.next = null;
        if (isEmpty()) head = tail;
        else oldTail.next = tail;

        n++;
    }

    @Override
    public Item dequeue() {
        final Item item = head.item;
        head = head.next;
        if (isEmpty()) tail = null;

        n--;
        return item;
    }

    private class Node extends Linked<Item>.AbstractNode {
        private Item item;
        /** this的下一个节点 */
        private Node next;

        private Node() {
        }

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }

        @Override
        protected Linked<Item>.AbstractNode next() {
            return next;
        }

        @Override
        protected Item item() {
            return item;
        }
    }
}
