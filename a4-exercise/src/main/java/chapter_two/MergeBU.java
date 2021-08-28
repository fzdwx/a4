package chapter_two;

/**
 * 自底向上的归并排序
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/28 16:27
 */
public class MergeBU extends AbstractMerge {

    @Override
    public void sort(Comparable[] a) {
        final int n = a.length;
        aux = new Comparable[n];

        for (int i = 1; i < n; i = i + i) {
            // System.out.println("i : = " + i);

            for (int lo = 0; lo < n - i; lo += i + i) {
                final int hi = Math.min(lo + i + i - 1, n - 1);
                merge(a, lo, lo + i - 1, hi);

                // printIndent(i);
                // System.out.println("merge(a," + lo + "," + (lo + i - 1) + "," + hi + ")");
            }
        }
    }
}
