package chapter_two;

/**
 * 使用无需数组实现的优先级队列，暂未考虑扩容
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/28 17:59
 */
public class ArrayMaxPQ<Key extends Comparable<Key>> implements MaxPQ<Key> {

    private Key[] k;
    private int i = 0;

    public ArrayMaxPQ() {
        this(16);
    }

    public ArrayMaxPQ(int max) {
        this.k = ( Key[] ) new Comparable[max];
    }

    public ArrayMaxPQ(Key[] k) {
        this.k = k;
    }

    public static void main(String[] args) {
        final ArrayMaxPQ<Integer> q = new ArrayMaxPQ<>();
        q.insert(2);
        q.insert(1);
        q.insert(10);
        q.insert(3);
        q.insert(4);
        q.insert(6);

        System.out.println(q.delMax());


        System.out.println(q);
    }

    @Override
    public void insert(Key v) {
        k[i++] = v;
    }

    @Override
    public Key max() {
        int max = 0;
        for (int j = 0; j < i; j++) {
            if (k[j].compareTo(k[max]) > 0) {
                max = j;
            }
        }
        return k[max];
    }

    @Override
    public Key delMax() {
        int max = 0;
        for (int j = 0; j < i; j++) {
            if (k[j].compareTo(k[max]) > 0) {
                max = j;
            }
        }
        final int i = this.i - 1;
        Key temp = k[i];
        k[i] = k[max];
        k[max] = temp;

        k[i] = null;
        this.i--;
        return k[max];
    }

    @Override
    public boolean isEmpty() {
        return k.length == 0;
    }

    @Override
    public int size() {
        return k.length;
    }

    @Override
    public String toString() {
        if (k == null)
            return "null";

        int iMax = this.i - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; i < this.i; i++) {
            b.append(k[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
        return b.toString();
    }
}
