package org.atomic.infrastructure.collection;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/1 14:24
 */
class LinkedListTest {

    @Test
    void test() {
        final LinkedList<String> list = LinkedList.of();
        list.add("123");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("13");
        list.add("15");
        list.add("4");
        System.out.println(list);

        list.remove("4");
        System.out.println(list);

        list.print(list.reverse());
    }

}