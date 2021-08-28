package chapter_two;

/**
 * 归并排序抽象
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/28 16:24
 */
public abstract class AbstractMerge implements RecursionDebugAble, Sortable {

    // 归并排序所需的辅助数组
    protected Comparable[] aux;

    protected void merge(Comparable[] a, int lo, int mid, int hi) {
        int l = lo, r = mid + 1;
        // copy lo ~ hi
        if (hi + 1 - lo >= 0)
            System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        for (int i = lo; i <= hi; i++) {
            if (l > mid)  // 左边取完，取右边
                a[i] = aux[r++];
            else if (r > hi) // 右边取完，取左边
                a[i] = aux[l++];
            else if (less(aux[r], aux[l])) // 右边小于左边，取右边
                a[i] = aux[r++];
            else // 右边大于等于左边，取左边
                a[i] = aux[l++];
        }
    }
}
