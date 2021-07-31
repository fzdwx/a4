/**
 * Description: 辅助程序 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 12:12:27
 */
public class BootStrap {

    public static void main(String[] args) {
        boolean[][] twoDArray = {
                {true, false, false}
                , {true, false, false}
                , {true, true, false}
                , {true, false, true}
                , {true, true, false}
        };

        int row = 0;
        final int cols = twoDArray[0].length;

        System.out.println(printCol(cols));
        for (boolean[] onwDArray : twoDArray) {

            System.out.print(row++ + " ");
            for (boolean b : onwDArray) {
                if (b) {
                    System.out.print("\t*");
                } else
                    System.out.print("\t ");
            }
            System.out.println();
        }
    }

    private static String printCol(int cols) {
        StringBuilder res = new StringBuilder("\t");
        for (int i = 0; i <= cols; i++) {
            res.append(i).append("\t");
        }
        return res.toString();
    }
}
