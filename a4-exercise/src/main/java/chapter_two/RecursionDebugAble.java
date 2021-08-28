package chapter_two;

/**
 * 递归debug
 * <p>
 * 在递归函数的开头，调用 printIndent(count++) 并打印关键变量；
 * 然后在所有 return 语句之前调用 printIndent(--count) 并打印返回值。
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/28 15:41
 */
public interface RecursionDebugAble {

    // 全局变量，记录递归函数的递归层数
    // int count = 0;

    // 输入 n，打印 n 个 tab 缩进
    default void printIndent(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("   ");
        }
    }
}
