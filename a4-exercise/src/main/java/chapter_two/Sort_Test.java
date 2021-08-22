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

    public void demo(Class<? extends Sortable> clazz) {
        Integer[] a = new Integer[]{4, 2, 6, 4, 7};


        final Sortable sortable = ReflectUtil.newInstance(clazz);

        sortable.sort(a);

        sortable.show(a);
    }
}
