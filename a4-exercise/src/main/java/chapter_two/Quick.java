package chapter_two;

import stdlib.StdRandom;

/**
 * 快速排序
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @module
 * @date 2021-08-28 16:51:15
 * @see Sortable
 */
public class Quick implements Sortable {

    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a); // 打乱数组
        sort(a, 0, a.length - 1);
    }

    protected void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int p = partition(a, lo, hi);
        sort(a, lo, p - 1); // a[lo..p-1]
        sort(a, p + 1, hi); // a[p+1..hi]
    }

    /**
     * 将数组切分为a[lo..i-1],a[i],a[i+1..hi]
     */
    protected int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1; // 左右扫描指针
        Comparable v = a[lo]; // 切分元素
        while (true) {
            // 扫描左右，检查是否结束并交换元素
            while (Sortable.less(a[++i], v)) if (i == hi) break;
            while (Sortable.less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            Sortable.exchange(a, i, j);
        }
        Sortable.exchange(a, lo, j);

        return j;
    }
}
