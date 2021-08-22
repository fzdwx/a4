package chapter_two;

/**
 * 排序公共接口定义
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/21 15:20
 */
public interface Sortable {

    /** 将数组a升序排列 */
    void sort(Comparable[] a);

    /** is v < w ? */
    default boolean less(Comparable v, Comparable<?> w) {
        return v.compareTo(w) < 0;
    }

    /** exchange a[i] and a[j] */
    default void exchange(Comparable[] a, int i, int j) {
        final Comparable<? extends Object> temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    /** 打印 数组a */
    default void show(Comparable[] a) {
        for (Comparable tComparable : a) {
            System.out.print(tComparable + " ");
        }
        System.out.println();
    }

    /** 数组a是否是按从小到大排列的 */
    default boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }
}
