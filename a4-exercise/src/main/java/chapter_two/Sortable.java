package chapter_two;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/21 15:20
 */
public interface Sortable {

    void sort(Comparable[] a);

    default boolean less(Comparable v, Comparable<?> w) {
        return v.compareTo(w) < 0;
    }

    default void exchange(Comparable[] a, int i, int j) {
        final Comparable<? extends Object> temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    default void show(Comparable[] a) {
        for (Comparable tComparable : a) {
            System.out.print(tComparable + " ");
        }
        System.out.println();
    }

    default boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

}
