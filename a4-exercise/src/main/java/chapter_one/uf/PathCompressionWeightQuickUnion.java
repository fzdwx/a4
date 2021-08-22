package chapter_one.uf;

/**
 * 实现了路径压缩以及加权的 quick union
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/22 12:25
 */
public class PathCompressionWeightQuickUnion extends WeightQuickUnion {

    /**
     * 以整数标识（0 ~ n-1）初始化n个触点
     *
     * @param n n
     *
     * @return {@link chapter_one.uf.UF}
     */
    public PathCompressionWeightQuickUnion(int n) {
        super(n);
    }

    @Override
    public int find(int p) {
        // 找到这个根触点
        while (p != id[p]) {
            // 路径压缩
            id[p] = id[id[p]];
            p = id[p];

        }
        return p;
    }
}
