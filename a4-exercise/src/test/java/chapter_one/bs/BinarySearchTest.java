package chapter_one.bs;

import chapter_one.BinarySearch;
import cn.hutool.core.io.FileUtil;
import org.junit.jupiter.api.Test;
import stdlib.In;
import stdlib.StdOut;

import java.util.Arrays;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/7/31 14:03
 */
class BinarySearchTest {

    @Test
    void rank() {
        final int[] whitelist = new In(FileUtil.file("tinyAllowlist.txt")).readAllInts();
        final int[] needTestList = new In(FileUtil.file("tinyText.txt")).readAllInts();

        Arrays.sort(whitelist);

        for (int key : needTestList) {
            if (BinarySearch.rank(key, whitelist) < 0) {
                StdOut.println("不在白名单中:" + key);
            }
        }
    }
}