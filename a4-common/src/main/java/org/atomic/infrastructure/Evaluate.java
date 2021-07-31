package org.atomic.infrastructure;

import java.util.LinkedList;

/**
 * Description: 算数表达式求值算法 <br>
 * <pre>
 *  expression = ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
 *  expect = 101.0
 * </pre>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 17:43:20
 */
public class Evaluate {

    private final static String DEMO_EXPRESSION = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
    private final static double DEMO_EXPECT = 101.0;

    /**
     * 算法思路:
     * 1.忽略 ( 左括号
     * 2.将操作符入栈
     * 3.将数值入栈
     * 4.遇到 ) 括号，先弹出一个操作符，然后弹出所需的数值，根据对应的规则进行计算。最后将数值入栈.
     *
     * @param expression 表达式
     * @return double
     */
    public static double of(String expression) {
        final LinkedList<String> ops = new LinkedList<>();
        final LinkedList<Double> vals = new LinkedList<>();

        for (String expr : expression.split(" ")) {
            switch (expr) {
                case "(":// nothing to do
                    break;
                case "+":
                case "/":
                case "-":
                case "*":
                case "sqrt":
                    ops.push(expr);
                    break;
                case ")":
                    final String op = ops.pop();
                    Double val = vals.pop();

                    if (op.equals("+")) val = vals.pop() + val;
                    if (op.equals("-")) val = vals.pop() - val;
                    if (op.equals("*")) val = vals.pop() * val;
                    if (op.equals("/")) val = vals.pop() / val;
                    if (op.equals("sqrt")) val = Math.sqrt(val);

                    vals.push(val);
                    break;
                default:
                    vals.push(Double.parseDouble(expr));
                    break;
            }
        }
        return vals.pop();
    }
}
