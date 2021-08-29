package chapter_two;

import chapter_two.priorityQueue.ArrayMaxPQ;
import chapter_two.priorityQueue.HeapMaxPQ;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/29 17:30
 */
public class PQTest {

    Integer[] keys = {
            11, 3, 9, 5, 4, 7, 6, 333, 123
    };

    @Test
    void test_heap() {
        final HeapMaxPQ<Integer> q = new HeapMaxPQ<>(keys);
        for (Integer i : q) {
            System.out.println(i);
        }
    }

    @Test
    void test_array() {
        final ArrayMaxPQ<Integer> q = new ArrayMaxPQ<>(keys);
        for (Integer i : q) {
            System.out.println(i);
        }
    }
}
