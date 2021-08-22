package chapter_one.uf;

/**
 * 实现了加权的 quick union
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/21 14:44
 */
public class WeightQuickUnion extends QuickUnion {

    private final int[] sz; // (由触点索引的)，各个根节点对应的分量大小。

    /**
     * 以整数标识（0 ~ n-1）初始化n个触点
     *
     * @param n n
     *
     * @return {@link UF}
     */
    public WeightQuickUnion(int n) {
        super(n);
        sz = new int[n];
        for (int i = 0; i < sz.length; i++) {
            sz[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        final int i = find(p);
        final int j = find(q);
        if (i == j) return;

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }

        count--;
    }
}
