package org.atomic.infrastructure.collection;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/7/31 20:01
 */
public interface Collection<Item> {

    /**
     * 当前集合是否为空
     *
     * @return boolean
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 返回当前集合元素的个数
     *
     * @return int
     */
    int size();
}
