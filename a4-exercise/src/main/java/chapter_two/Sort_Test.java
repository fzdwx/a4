package chapter_two;

import cn.hutool.core.util.ReflectUtil;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/22 9:21
 */
public class Sort_Test {

    @Test
    public void test_selection() {
        demo(Selection.class);
    }

    @Test
    public void test_insertion() {
        demo(Insertion.class);
    }

    @Test
    public void test_merge_in_place() {
        demo(Merge.class);
    }

    @Test
    public void test_merge_in_place_bu() {
        demo(MergeBU.class);
    }

    @Test
    public void test_quick() {
        demo(Quick.class);
    }

    @Test
    public void test_quick_v2() {
        demo(QuickV2.class);
    }

    @Test
    public void test_quick_3way() {
        demo(Quick3way.class);
    }

    public void demo(Class<? extends Sortable> clazz) {
        Integer[] a = new Integer[]{4, 2, 6, 1, 7, 13, 8, 9, 11, 10, 222, 376, 17, 6666, 1231785, 12356};


        final Sortable sortable = ReflectUtil.newInstance(clazz);

        sortable.sort(a);

        Sortable.show(a);
    }
}
