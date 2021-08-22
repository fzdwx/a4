package chapter_two;

/**
 * <h2>希尔排序·</h2>
 * 基于插入排序的一种快速排序算法，对于大规模数组插入排序很慢，因为它只会教诲相邻的元素，因此元素只能一点一点的从数组的一端移动到另一端。
 * 例如，如果主键最小的元素正好在数组的尽头，要将它挪到正确的位置就需要n-1次移动。
 * <p>
 * 希尔排序为了加快速度简单的改进了插入排序，交换不相邻的元素
 * 以对数组的局部进行排序，并最终用插入排序将局部有序的数组进行排序。
 * <ol>
 *     <li>使数组中任意间隔为h的元素都是有序的，这样的数组被称为h有序数组</li>
 *     <li>换句话说，一个h有序数组就是h个相互独立的有顺序的数组</li>
 *     <li>在进行排序时，如果h很大，就能将元素移动到很原的地方，为了实现更小的h有序创造方便</li>
 *     <li>对于任意以1结尾的h序列，我们都能将数组排序</li>
 * </ol>
 * <p>
 * 这里使用序列 1 / 2 (3 ^ k -1)。从n / 3开始递减至1.
 * <p>
 * 通俗的说，就是每隔h个索引，取出一个元素，组成一个h数组，然后对这个数组进行插入排序。
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @module
 * @date 2021-08-22 10:24:42
 * @see Sortable
 */
public class Shell extends Insertion {

    @Override
    public void sort(Comparable[] a) {
        final int n = a.length;
        int h = 1;

        while (h < n / 3) h = 3 * h - 1; // 1 4 13 40 121 364 1093...
        while (h >= 1) {
            // step = h,每隔h步长进行插入排序
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exchange(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
