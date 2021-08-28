package chapter_two;

/**
 * 快速排序的3向切分
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/28 17:26
 */
public class Quick3way extends Quick {

    @Override
    protected void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        int lt = lo, i = lo + 1, gt = hi;

        Comparable v = a[lo]; // 切分元素
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) Sortable.exchange(a, lt++, i++);
            else if (cmp > 0) Sortable.exchange(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
}
