package org.atomic.infrastructure.collection;

import java.util.Iterator;
import java.util.Objects;

/**
 * Description: 链表 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 21:00:08
 * @see Linked
 * @see Collection
 */
public class LinkedList<Item> extends Linked<Item> implements Collection<Item> {

    /** 头节点，当前链表的第一个节点 */
    private Node first;
    /** 尾节点，当前链表的最后一个节点 */
    private Node last;
    /** 当前链表的元素个数 */
    private int n;

    // Initialization method start
    public LinkedList() {
    }

   /* public LinkedList<Item> of(Iterable<Item> collection) {
        final LinkedList<Item> of = of();

    }*/

    public static <Item> LinkedList<Item> of() {
        return new LinkedList<>();
    }
    // Initialization method end

    /**
     * 删除链表中所有item和key相同的节点
     */
    public static <Item> void remove(LinkedList<Item> list, Item key) {
        // TODO: 2021/7/31  remove
    }

    /**
     * 添加元素到链表中
     *
     * @param item 元素
     */
    public void add(Item item) {
        add(n, item);
    }

    /**
     * 添加元素到链表指定的索引
     *
     * @param index 需要添加元素的索引
     * @param item  元素
     */
    public void add(int index, Item item) {
        checkIndexToAdd(index);
        if (index == n) {
            linkLast(item);
        } else
            linkBefore(item, node(index));
    }

    /**
     * 删除指定索引处的元素
     */
    public Item delete(int index) {
        checkIndex(index);
        return unlink(node(index));
    }

    /**
     * 删除指定元素
     */
    @SuppressWarnings("all")
    public boolean delete(Item item) {
        for (Node f = this.first; f != null; f = f.next) {
            if (f.item.equals(item)) {
                unlink(f);
                return true;
            }
        }
        return false;
    }

    /**
     * 清空当前链表
     */
    public void clear() {
        while (first != null) {
            unlink(first);
            first = first.next;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    @Override
    public int size() {
        return n;
    }

    /**
     * 取消连接
     * <pre>
     *     prev node next => prev next
     * </pre>
     */
    private Item unlink(Node node) {
        final Item item = node.item;
        final Node next = node.next;
        final Node prev = node.prev;

        if (prev == null) this.first = next; // node是首节点
        else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) this.last = prev; // node是最后一个节点
        else {
            next.prev = prev;
            node.next = null;
        }

        node.item = null;

        n--;
        return item;
    }

    /**
     * 插入一个元素到next前面
     * <pre>
     *     1.如果next.prev为null,就说明是添加到first节点
     *     2.其他情况：正常连接
     * </pre>
     *
     * @param item 元素
     * @param next 节点
     */
    private void linkBefore(Item item, Node next) {
        // prev next => prev insert next
        final Node prev = next.prev;
        final Node insert = new Node(item, prev, next);
        next.prev = insert;

        if (Objects.isNull(prev)) {
            this.first = insert;
        } else prev.next = insert;
        n++;
    }

    /**
     * 插入一个元素到最后
     * <pre>
     *     1.如果是第一次添加，首尾都是同一个节点
     *     2.其他情况：first last => first last newLast
     * </pre>
     *
     * @param item 项
     */
    private void linkLast(Item item) {
        // first ... prev => first ... prev  last
        final Node prev = this.last;
        final Node last = new Node(item, prev, null);
        this.last = last;

        if (Objects.isNull(this.first)) {
            this.first = last;
        } else prev.next = last;
        n++;
    }

    private void checkIndexToAdd(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index, size()));
        }
    }

    /**
     * 检查index是否越界
     */
    private void checkIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index, size() - 1));
        }
    }

    /**
     * 构造一个 IndexOutOfBoundsException 详细消息。 在错误处理代码的许多可能重构中，这种“概述”在服务器和客户端 VM 上表现最佳
     */
    private String outOfBoundsMsg(int index, int biggest) {
        return "Your Input Index: " + index + ", Actually Size: " + size() + ", Your input index the biggest is " + biggest;
    }

    /**
     * 判断参数是否是迭代器或加法操作的有效位置的索引。
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size();
    }

    /**
     * 判断参数是否是现有元素的索引
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size();
    }

    Node node(int index) {
        int i = 0;
        if (index > n / 2) {
            Node f = this.first;
            while (i < index) {
                f = f.next;
                i++;
            }
            return f;
        } else {
            i = n - 1;
            Node l = this.last;
            while (i > index) {
                l = l.prev;
                i--;
            }
            return l;
        }
    }

    /**
     * Description: 链表节点 <br>
     *
     * @author <a href="mailto:likelovec@gmail.com">like</a>
     * @date 2021-07-31 21:28:17
     * @see Node
     */
    private class Node extends Linked<Item>.AbstractNode {
        private Item item;
        /** this的下一个节点 */
        private Node next;
        /** this的上一个节点 */
        private Node prev;

        private Node() {
        }

        public Node(Item item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
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
