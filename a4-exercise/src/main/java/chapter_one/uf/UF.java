package chapter_one.uf;

/**
 * 一开始我们有n个连通分量，每个触点都可以看作是一个它自己的连通分量，
 * 因此我们初始化id[i]的值初始化为i，i在0 ~ n-1之间。对于每个触点 i，
 * 我们将用{@link chapter_one.uf.UF#find}来判定它所在的的分量的信息保存在id[i]之中。
 * {@link chapter_one.uf.UF#connected} 只用<code>find(p) == find(q)</code>，
 * 它返回一个bool，我们在所有方法的实现都需要用{@link chapter_one.uf.UF#connected}。
 *
 * <pre>
 *    Data files:   https://algs4.cs.princeton.edu/15uf/tinyUF.txt
 *                  https://algs4.cs.princeton.edu/15uf/mediumUF.txt
 *                  https://algs4.cs.princeton.edu/15uf/largeUF.txt
 * </pre>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/21 10:46
 */
public abstract class UF {

    protected final int[] id; // 连通分量的id（以触点为索引）
    protected int count; // 连通分量数量

    /**
     * 以整数标识（0 ~ n-1）初始化n个触点
     *
     * @param n n
     *
     * @return {@link chapter_one.uf.UF}
     */
    public UF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    /**
     * 在p和q之间添加一条连接
     */
    public abstract void union(int p, int q);

    /**
     * 如果p和q存在于同一个连通分量中则返回true
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * p(0 ~ n-1)所在的连通分量的标识符
     */
    public abstract int find(int p);

    /**
     * 返回连通分量的数量
     */
    public int count() {
        return count;
    }
}
