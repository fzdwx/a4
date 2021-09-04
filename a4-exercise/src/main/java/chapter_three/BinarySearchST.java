package chapter_three;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 二叉搜索树
 *
 * <pre>
 *
 *     1.每个节点的key都必须是可比较的
 *     2.一个二叉搜索树分为左子树和右子树
 *     3. x.left.key < x.key < x.right.key
 * </pre>
 * 本实现主要采用递归实现{@code put}以及{@code get}
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @module
 * @date 2021-09-02 20:35:26
 * @see OrderlyST
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> extends OrderlyST<Key, Value> {

    // 当前树的根节点
    private Node<Key, Value> root;

    public static void main(String[] args) {
        BinarySearchST<String, String> map = new BinarySearchST<>();
        map.put("name", "like");
        map.put("age", "18");
        map.put("address", "WuHan");
        map.put("province", "HuBei");

        map.print(map.root);
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    @Override
    public void delete(Key key) {
        delete(root, key);
    }

    @Override
    public Key min() {
        return min(root).key;
    }

    @Override
    public Key max() {
        return max(root).key;
    }

    @Override
    public Key floor(Key key) {
        Node<Key, Value> x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    @Override
    public Key ceiling(Key key) {
        Node<Key, Value> x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    @Override
    public int rank(Key key) {
        return rank(key, root);
    }

    @Override
    public Key select(int no) {
        return select(root, no).key;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        ArrayList<Key> list = new ArrayList<>();
        keys(root, list, lo, hi);
        return list;
    }

    @Override
    public void deleteMin() {
        Node<Key, Value> x = deleteMin(root);
    }

    @Override
    public void deleteMax() {
        Node<Key, Value> x = deleteMax(root);
    }

    private void keys(Node<Key, Value> x, Collection<Key> collection, Key lo, Key hi) {
        if (x == null) return;
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);

        if (cmpLo < 0) keys(x.left, collection, lo, hi);
        if (cmpLo <= 0 && cmpHi >= 0) collection.add(x.key);
        if (cmpHi > 0) keys(x.right, collection, lo, hi);
    }

    private Node<Key, Value> delete(Node<Key, Value> x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node<Key, Value> t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) - 1;
        return x;
    }

    private Node<Key, Value> deleteMax(Node<Key, Value> x) {
        if (x.right == null) {
            return x.left;
        }

        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node<Key, Value> deleteMin(Node<Key, Value> x) {
        if (x.left == null) {
            return x.right;
        }

        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private int rank(Key key, Node<Key, Value> x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);

        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    private Node<Key, Value> select(Node<Key, Value> x, int no) {
        if (x == null) return null;
        int t = size(x.left);

        if (t > no) return select(x.left, no);
        else if (t < no) return select(x.right, no - t - 1);
        else return x;
    }

    private Node<Key, Value> max(Node<Key, Value> x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    private Node<Key, Value> min(Node<Key, Value> X) {
        if (X.left == null) return X;
        return min(X.left);
    }

    private Node<Key, Value> floor(Node<Key, Value> x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node<Key, Value> t = floor(x.right, key);
        if (t != null) return t;
        return x;
    }

    private Node<Key, Value> ceiling(Node<Key, Value> x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node<Key, Value> t = ceiling(x.left, key);
        if (t != null) return t;
        return x;
    }

    /**
     * 如果key存在于以x为根节点的子树中则更新它的值；
     * 否则将以key和val为键值对的新节点插入到该子树中
     */
    private Node<Key, Value> put(Node<Key, Value> x, Key key, Value value) {
        if (x == null) return Node.of(key, value, 1);

        int cmp = key.compareTo(x.key);

        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;

        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private void print(Node x) {
        if (x == null) return;

        print(x.left);
        System.out.println(x.key);
        print(x.right);
    }

    /**
     * 在x为根节点的树上，查找key对应的值
     */
    private Value get(Node<Key, Value> x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;
    }

    /**
     * 返回这棵树的大小
     */
    private int size(Node<Key, Value> root) {
        if (root == null) return 0;
        return root.size;
    }

    /**
     * 二叉树的节点
     * <pre>
     *     1.当前节点总是大于左子树的根节点，小于右子树根节点 left < x < right
     * </pre>
     */
    private static class Node<Key, Value> {
        private Key key;
        private Value value;
        private Node<Key, Value> left; // 左子树
        private Node<Key, Value> right; // 右子树
        private int size; // 当前树的节点数量 size(x) = size(x.left) + size(right) +1

        private Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }

        @Override
        public String toString() {
            return key + " : " + value;
        }

        /**
         * 构造节点
         *
         * @return {@link Node}<{@link Key}, {@link Value}>
         */
        static <Key, Value> Node<Key, Value> of(Key key, Value value, int size) {
            return new Node<Key, Value>(key, value, size);
        }
    }
}
