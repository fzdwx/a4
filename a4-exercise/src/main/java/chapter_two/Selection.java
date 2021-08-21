package chapter_two;

/**
 * <h2>选择排序</h2>
 * <ol>
 *     <li>首先找到数组中最小的数</li>
 *     <li>其次，把它和数组中第一个元素交换位置（如果第一个元素就是最小的元素，那么它就和自己教诲）</li>
 *     <li>再次，在剩下的元素中找到最小的元素，将她和数组第二个元素教诲位置</li>
 * </ol>
 * 如此往复，直到将整个数组排序。这种方法叫~。
 * <p/>
 * 选择排序的内循环只是在比较当前元素和目前已知最小元素。交换元素的代码写在内循环之外，每次交换都能排定一个元素。
 * 所以交换的总次数是n.
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-08-21 16:26:17
 * @see Sortable
 */
public class Selection implements Sortable {

    @Override
    public void sort(Comparable[] a) {
        final int n = a.length;

        for (int i = 0; i < n; i++) {
            // 将a[i] 和 a[i+1...n]中最小的数交换
            int min = i; // 最小元素的索引
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) min = j; // 如果当前遍历的数比我们记录的数还小，就交换索引
            }
            exchange(a, i, min); // 交换
        }
    }
}
