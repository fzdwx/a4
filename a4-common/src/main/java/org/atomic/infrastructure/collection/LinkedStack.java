package org.atomic.infrastructure.collection;

import java.util.Iterator;

/**
 * Description: 链表实现的栈 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 19:34:07
 * @see Linked
 * @see Stack
 */
public class LinkedStack<Item> extends Linked<Item> implements Stack<Item> {

    /** 栈顶 */
    private Node head;
    /** 元素个数 */
    private int n;

    @Override
    public void push(Item item) {
        // head => head oldHead
        final Node oldHead = this.head;
        head = new Node(item);
        head.next = oldHead;
        n++;
    }

    @Override
    public Item pop() {
        final Item item = head.item;
        head = head.next;
        n--;
        return item;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator<>(head);
    }

    private class Node extends AbstractNode<Item> {
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
        protected AbstractNode<Item> next() {
            return next;
        }

        @Override
        protected Item item() {
            return item;
        }
    }
}
