package chapter_one.uf;

/**
 * <h2>quick-find算法</h2>
 * 在同一个连通分量中的所有触点在id[]中的值是相同的。这说明connected(q,p)只需要判断id[p] == id[q],
 * 当且仅当p和q都在同一条连通分量才返回true。为了调用union(q,p)确保这一点：
 * <pre>
 *     1.检查他们是否已经存在于同一个连通分量
 *     2.如果是就不采取任何动作
 *     3.否则我们将面对的情况：
 *      - p所在的连通分量中的所有触点的id[]值都相同
 *      - q所在的连通分量中的所有触点的id[]值都是另一个
 *     4.要将两个分量合二为一，就必须将两个集合中所有触点对应的id[]值变成同一个
 *     5.遍历整个数组，将所有和id[p]相等的值变为id[q]的值，当然也可以反着来。
 * </pre>
 *
 * <h2>分析</h2>
 * 每次find()只需要访问一次。
 * 每次union()需要访问的次数为（n+3） ~ (2n+1) 之间
 *
 * <h3>缺点</h3>
 * 无法处理大型问题，假如我们用这个算法解决动态连通性问题，并且最后只得到一个连通分量，那么至少需要调用n-1次union()方法，
 * 即（n+3）(n-1) ~ n * n次数组访问。
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/21 12:43
 */
public class QuickFind extends UF {

    public QuickFind(int n) {
        super(n);
    }

    @Override
    public void union(int p, int q) {
        final int pId = find(p);
        final int qId = find(q);

        // 如果已经在相同的分量中，就返回
        if (pId == qId) return;

        // 将p的分量重命名为q的
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) id[i] = qId;
        }
        count--;
    }

    @Override
    public int find(int p) {
        return id[p];
    }
}
