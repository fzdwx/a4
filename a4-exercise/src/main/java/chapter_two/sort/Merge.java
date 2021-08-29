package chapter_two.sort;

/**
 * 自顶向下归并排序
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @module
 * @date 2021-08-28 15:00:59
 */
public class Merge extends AbstractMerge {

    int count;

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        // System.out.println("sort(a," + lo + "," + hi + ")");
        // printIndent(++count);

        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid); // 排序左半边
        sort(a, mid + 1, hi); // 排序右半边

        // System.out.println("merge(a," + lo + "," + mid + "," + hi + ")");
        // printIndent(--count);

        merge(a, lo, mid, hi); //归并结果
    }
}
