package chapter_one.uf;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ReflectUtil;
import org.junit.jupiter.api.Test;
import util.DownLoadFileUtil;

import java.io.File;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/21 11:16
 */
public class UF_Test {

    public static void demo(Class<? extends UF> clazz) {
        final ThreadPoolExecutor executor = ThreadUtil.newExecutor(3, 3);

        ListUtil.of(
                "https://algs4.cs.princeton.edu/15uf/tinyUF.txt"
                // , " https://algs4.cs.princeton.edu/15uf/mediumUF.txt"
                // , " https://algs4.cs.princeton.edu/15uf/largeUF.txt"
        ).stream().forEach(url -> {
            // executor.submit(() -> {
            final String path = DownLoadFileUtil.download(url);
            final FileReader reader = FileReader.create(new File(path));

            final List<String> data = reader.readLines();
            final UF uf = ReflectUtil.newInstance(clazz, Integer.parseInt(data.get(0)));
            for (int i = 1; i < data.size(); i++) {
                final String[] split = data.get(i).split(" ");
                int p = Integer.parseInt(split[0]);
                int q = Integer.parseInt(split[1]);

                if (uf.connected(p, q)) continue;
                uf.union(p, q);

                System.out.println(p + " " + q);
            }
            System.out.println(uf.count + " components");
            // });
        });
    }

    @Test
    public void test_find() {
        demo(QuickFind.class);
    }

    @Test
    public void test_union() {
        demo(QuickUnion.class);
    }

    @Test
    public void test_weight_union() {
        demo(WeightQuickUnion.class);
    }
}
