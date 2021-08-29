package chapter_two.sort;

/**
 * 堆排序
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @module
 * @date 2021-08-29 19:22:48
 * @see Sortable
 */
public class Heap implements Sortable {

    private Heap() {}


    public void sort(Comparable[] pq) {
        int n = pq.length;

        // heapify phase
        for (int k = n / 2; k >= 1; k--)
            sink(pq, k, n);

        // sortdown phase
        int k = n;
        while (k > 1) {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
    }

    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }
}
