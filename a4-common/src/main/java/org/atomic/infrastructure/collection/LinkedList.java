package org.atomic.infrastructure.collection;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

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
     * 以传入节点作为root节点，打印他以及他的next的信息。
     */
    public void print(Node node) {
        final String nodeInfo = print.apply(new LinkedIterator<>(node));

        System.out.println(nodeInfo);
    }

    /**
     * 删除链表中所有item和key相同的节点
     */
    public void remove(Item key) {
        if (key == null) return;

        ResizingArrayStack<Node> stack = new ResizingArrayStack<>();
        traverse((node) -> {
            if (key.equals(node.item)) stack.push(node);
        });

        Node pop;
        while ((pop = stack.pop()) != null) {
            unlinkNoReturn(pop);
        }
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
        AtomicBoolean flag = new AtomicBoolean(false);

        traverse((node) -> {
            if (node.item.equals(item)) {
                unlink(node);
                flag.set(true);
                return;
            }
        });

        return flag.get();
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
     * 遍历
     */
    public void traverse(Visitor<Item> visitor) {
        for (Node f = first; f != null; f = f.next) {
            visitor.visit(f);
        }
    }


    /**
     * 翻转链表，以node节点作为第一个节点
     * <pre>
     *     迭代：记录链表中三个连续的节点：reverse、first、second
     *       1. 在每轮迭代中，从原链表中提取first，并将它插入到逆链表的头节点
     *       2. 需要一直保持first是指向原链表中所有剩余节点的首节点
     *       3. second指向原链表中所有剩余节点的第二个节点，
     *       4. reverse指向结果链表的头节点
     * </pre>
     */
    public Node reverseUseIteration(Node x) {
        if (x == null) return null;

        Node first = x;
        Node reverse = null;

        while (first != null) {
            final Node second = first.next;  // 迭代，temp

            first.next = reverse;
            reverse = first;

            first = second;
        }
        return reverse;
    }

    /**
     * 翻转链表:使用递归
     * <pre>
     *     假设链表有N个节点，先递归颠倒最后第N-1节点，然后将原链表中的首节点插入到结果链表的尾部
     * </pre>
     */
    public Node reverseUseRecursion(Node x) {
        if (x == null) return null;
        if (x.next == null) return x;

        final Node second = first.next;
        final Node rest = reverseUseRecursion(second);

        second.next = first;
        first.next = null;

        return rest;
    }

    /**
     * 反转当前链表
     */
    public Node reverse() {
        return reverseUseIteration(this.first);
    }

    /**
     * 接收一个链表节点作为参数并删除该节点的后续节点
     */
    private Item removeAfter(Node node) {
        if (node == null || node.item == null || node.next == null) {
            return null;
        }
        return unlink(node.next);
    }

    /**
     * 取消连接
     * <pre>
     *     prev node next => prev next
     * </pre>
     */
    private Item unlink(Node node) {
        final Item item = node.item;
        unlinkNoReturn(node);
        return item;
    }

    private void unlinkNoReturn(Node node) {
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
    }

    /**
     * 接收两个链表节点作为参数，将第二节点插入链表并使之成为第一个节点的后续节点
     * <pre>
     *     before next => before after next
     * </pre>
     */
    private void linkAfter(Node before, Item afterItem) {
        final Node next = before.next;

        final Node after = new Node(afterItem);
        if (next == null) {
            this.last = after;
        } else {
            after.next = next;
            after.prev = before;
        }
        before.next = after;
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

    @FunctionalInterface
    interface Visitor<Item> {

        void visit(LinkedList<Item>.Node node);
    }

    /**
     * Description: 链表节点 <br>
     *
     * @author <a href="mailto:likelovec@gmail.com">like</a>
     * @date 2021-07-31 21:28:17
     * @see Node
     */
    public class Node extends AbstractNode<Item> {
        private Item item;
        /** this的下一个节点 */
        private Node next;
        /** this的上一个节点 */
        private Node prev;

        private Node(Item item) {
            this.item = item;
        }

        private Node(Item item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
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
