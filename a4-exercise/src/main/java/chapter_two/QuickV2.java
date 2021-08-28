package chapter_two;

/**
 * 使用快速排序优化
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/28 17:04
 */
public class QuickV2 extends Quick {

    protected void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + 10) {
            for (int i = lo; i <= hi; i++) {
                for (int j = i; j > 0 && Sortable.less(a[j], a[j - 1]); j--) {
                    Sortable.exchange(a, j, j - 1);
                }
            }
            return;
        }
        int p = partition(a, lo, hi);
        sort(a, lo, p - 1); // a[lo..p-1]
        sort(a, p + 1, hi); // a[p+1..hi]
    }
}
