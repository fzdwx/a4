package org.atomic.infrastructure;

import cn.hutool.core.io.FileUtil;
import org.junit.jupiter.api.Test;
import stdlib.In;
import stdlib.StdOut;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/7/31 17:14
 */
class StaticSetOfIntsTest {

    @Test
    void f1() {
        final int[] whitelist = new In(FileUtil.file("..\\..\\..\\data\\tinyAllowlist.txt")).readAllInts();
        final int[] needTestList = new In(FileUtil.file("..\\..\\..\\data\\tinyText.txt")).readAllInts();

        final StaticSetOfInts<Integer> set = StaticSetOfInts.of(whitelist);
        for (int key : needTestList) {
            if (!set.has(key)) {
                StdOut.println("不在白名单中:" + key);
            }
        }
    }
}