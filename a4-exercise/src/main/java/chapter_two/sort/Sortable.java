package chapter_two.sort;

/**
 * 排序公共接口定义
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/21 15:20
 */
public interface Sortable {

    /** is v < w ? */
    static boolean less(Comparable v, Comparable<?> w) {
        return v.compareTo(w) < 0;
    }

    /** exchange a[i] and a[j] */
    static void exchange(Comparable[] a, int i, int j) {
        final Comparable<? extends Object> temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /** 打印 数组a */
    static void show(Comparable[] a) {
        for (Comparable tComparable : a) {
            System.out.print(tComparable + " ");
        }
        System.out.println();
    }

    /** 数组a是否是按从小到大排列的 */
    static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    /** 将数组a升序排列 */
    void sort(Comparable[] a);
}
