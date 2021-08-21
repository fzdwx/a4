package chapter_one.uf;

/**
 * 与{@link QuickFind} 基于同样的数据结构——以触点作为索引的id[]数组，但这些值的意义完全不同了，需要用它来定义更加
 * 复杂的数据结构。
 * <p>
 * <ol>
 *     <li>每个触点所对应的id[]元素都是同一个连通分量中的另一个触点的名称(也可能是它自己)——这种关系称为<i>链接</i></li>
 *     <li>
 *         在实现{@link QuickUnion#find}时，<b>从给定的触点开始，由它的链接得到另一个触点，再由这个触点链接到第3个，
 *         以此类推，直到到达一个<i>根触点</i>,即链接指向自己的触点（这个触点必将存在）。
 *         </b>
 *     </li>
 *     <li>
 *         当且仅当分别由两个触点开始的这个过程到达了同一个根触点时，它们就属于同一个连通分量中。为了保证这个过程的有效性，我们需要用{@link chapter_one.uf.QuickUnion#union}
 *         来保证。它的实现很简单：
 *            <b> 由p和q的链接分别找到它们的根触点。然后只需要将一个根触点链接到另一个，就可以将一个连通分量命名为另一个分量。</b>
 *     </li>
 * </ol>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/21 13:47
 */
public class QuickUnion extends UF {

    /**
     * 以整数标识（0 ~ n-1）初始化n个触点
     *
     * @param n n
     *
     * @return {@link UF}
     */
    public QuickUnion(int n) {
        super(n);
    }

    @Override
    public void union(int p, int q) {
        final int pRoot = find(p);
        final int qRoot = find(q);

        if (pRoot == qRoot) return;

        id[pRoot] = qRoot;

        count--;
    }

    @Override
    public int find(int p) {
        // 找到这个根触点
        while (p != id[p]) p = id[p];
        return p;
    }
}
