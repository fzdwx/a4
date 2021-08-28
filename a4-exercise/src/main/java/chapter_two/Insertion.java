package chapter_two;

/**
 * <h2>插入排序</h2>
 * 当前索引左边的所有元素都是有序的，但它们最终的位置还不确定，为了给更小的元素腾出位置，它们可能会被移动。但是当索引到达数组右端时，排序就完成了。
 * <p/>
 * 插入排序所需的时间取决于输入中元素的初始顺序，在实际应用中常见于非随机数组。
 * <ol>
 *     <li>首先假定当前索引位置的左边都是有序的</li>
 *     <li>然后当前索引的元素，一个个和前面有序的元素进行比较</li>
 *     <li>如果当前小于它的前一个就交换</l1>
 *     <l1></l1>
 * </ol>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @module
 * @date 2021-08-22 09:24:41
 */
public class Insertion implements Sortable {

    public static void Sort(Comparable[] a) {
        new Insertion().sort(a);
    }

    @Override
    public void sort(Comparable[] a) {
        final int n = a.length;

        // 假设i的左边都是排好序的
        for (int i = 1; i < n; i++) {
            // 将a[i]插入到 a[i-1]、a[i-2]、a[i-3]...中
            for (int j = i; j > 0 && Sortable.less(a[j], a[j - 1]); j--) {
                Sortable.exchange(a, j, j - 1);
            }
        }
    }
}
