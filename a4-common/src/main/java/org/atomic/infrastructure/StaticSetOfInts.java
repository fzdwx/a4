package org.atomic.infrastructure;

import java.util.Arrays;
import java.util.Objects;

/**
 * Description: 静态组整数 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 16:57:12
 */
public class StaticSetOfInts<Element extends Comparable<Element>> {

    private final Element[] elements;
    private final int ELEMENT_NOT_FOUNT = -1;

    // Initialization method start
    public StaticSetOfInts(Element[] elements) {
        this.elements = ( Element[] ) new Object[elements.length];
        // copy on write 保护性复制
        System.arraycopy(elements, 0, this.elements, 0, elements.length);
        // 保证有序性
        Arrays.sort(this.elements);
    }

    public StaticSetOfInts(int[] elements) {
        this.elements = ( Element[] ) new Integer[elements.length];
        for (int i = 0; i < elements.length; i++) {
            this.elements[i] = ( Element ) new Integer(elements[i]);
        }
        Arrays.sort(this.elements);
    }

    public static <Element extends Comparable<Element>> StaticSetOfInts<Element> of(Element[] elements) {
        return new StaticSetOfInts<>(elements);
    }

    public static StaticSetOfInts<Integer> of(int[] elements) {
        return new StaticSetOfInts<>(elements);
    }
    // Initialization method end

    /**
     * key 是否存在集合中
     *
     * @param key 需要查找的key
     * @return boolean
     */
    public boolean has(Element key) {
        return indexOf(key) != ELEMENT_NOT_FOUNT;
    }

    /**
     * 使用二分搜索
     *
     * @param key 需要查找的key
     * @return int
     */
    private int indexOf(Element key) {
        if (Objects.isNull(key)) return ELEMENT_NOT_FOUNT;

        int lo = 0, hi = elements.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key.compareTo(elements[mid]) < 0) hi = mid - 1;
            else if (key.compareTo(elements[mid]) > 0) lo = mid + 1;
            else return mid;
        }

        return ELEMENT_NOT_FOUNT;
    }
}
