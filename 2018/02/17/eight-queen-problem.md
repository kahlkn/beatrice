- Title: 八皇后问题
- Category:
- Tag: Java
- Author: Kahle
- Creation Time: 2018-02-17T10:25:39.666+0800
- Update Time: 2018-02-17T10:25:39.666+0800
- Original:
- Reference:

---


### 导语

在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。


### 正文

直接上代码：

```
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (0,n) (1,n) (2,n) (n,n)
 * (0,2) (1,2) (2,2) (n,2)
 * (0,1) (1,1) (2,1) (n,1)
 * (0,0) (1,0) (2,0) (n,0)
 */
public class EightQueens {

    public static class Point {

        public static void signDisabled(int[][] chessboard, int x, int y) {
            // chessboard must is foursquare
            for (int i = 0; i < chessboard.length; i++) {
                if (i != y) { chessboard[x][i] = -1; }
                if (i != x) { chessboard[i][y] = -1; }
            }
            for (int a = x, b = y; a >= 0 && b >= 0; a--, b--) {
                if (a != x && b != y) { chessboard[a][b] = -1; }
            }
            for (int a = x, b = y; a < chessboard.length && b < chessboard.length; a++, b++) {
                if (a != x && b != y) { chessboard[a][b] = -1; }
            }
            for (int a = x, b = y; a < chessboard.length && b >= 0; a++, b--) {
                if (a != x && b != y) { chessboard[a][b] = -1; }
            }
            for (int a = x, b = y; a >= 0 && b < chessboard.length; a--, b++) {
                if (a != x && b != y) { chessboard[a][b] = -1; }
            }
        }

        public static List<Point> takeUsablePoint(int[][] chessboard) {
            List<Point> result = new ArrayList<Point>();
            for (int x = 0; x < chessboard.length; x++) {
                for (int y = 0; y < chessboard.length; y++) {
                    if (chessboard[x][y] != 0) { continue; }
                    result.add(new Point(chessboard.length, x, y));
                }
            }
            return result;
        }

        private final int x;
        private final int y;
        private final int num;
        private boolean calculated;
        private int[][] chessboard;
        private List<Point> nextPoints;

        public Point(int num, int x, int y) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.calculated = false;
            this.chessboard = new int[num][];
            for (int i = 0; i < num; i++) {
                chessboard[i] = new int[num];
            }
            nextPoints = new ArrayList<Point>();
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getNum() {
            return num;
        }

        public boolean getCalculated() {
            return calculated;
        }

        public void setCalculated(boolean calculated) {
            this.calculated = calculated;
        }

        public int[][] getChessboard() {
            return chessboard;
        }

        public List<Point> getNextPoints() {
            return nextPoints;
        }

        public void calc(int[][] lastChessboard) {
            if (lastChessboard != null) {
                for (int i = 0; i < lastChessboard.length; i++) {
                    int[] tmp = new int[lastChessboard[i].length];
                    System.arraycopy(lastChessboard[i], 0, tmp, 0, lastChessboard[i].length);
                    chessboard[i] = tmp;
                }
            }
            chessboard[x][y] = 1;
            Point.signDisabled(chessboard, x, y);
            List<Point> points = Point.takeUsablePoint(chessboard);
            this.nextPoints.addAll(points);
            this.setCalculated(true);
        }

        public void printChessboard() {
            StringBuilder builder = new StringBuilder();
            for (int y = num - 1; y >= 0; y--) {
                builder.setLength(0);
                for (int x = 0; x < num; x++) {
                    int i = chessboard[x][y];
                    builder.append(i == -1 ? "!" : i == 0 ? "0" : "1").append(" ");
                }
                System.out.println(builder.toString());
            }
        }

    }

    private int num;
    private List<Point> points;
    private Map<String, int[][]> result;

    public EightQueens(int num) {
        this.num = num;
        points = new ArrayList<Point>();
        result = new HashMap<String, int[][]>();
        for (int x = 0; x < num; x++) {
            for (int y = 0; y < num; y++) {
                Point point = new Point(num, x, y);
                point.calc(null);
                this.points.add(point);
            }
        }
    }

    public void handlePoint(int layerNum, int num, Point lastPoint, Point point) {
        // layerNum start 1
        if (layerNum >= num) {
            point.calc(lastPoint.getChessboard());

            int[][] chessboard = point.getChessboard();
            StringBuilder builder = new StringBuilder();
            for (int x = 0; x < num; x++) {
                for (int y = 0; y < num; y++) {
                    if (chessboard[x][y] == 1) {
                        builder.append(x).append(y);
                    }
                }
            }
            if (!result.containsKey(builder.toString())) {
                result.put(builder.toString(), chessboard);
                System.out.println(result.size());
                point.printChessboard();
                System.out.println();
            }
            return;
        }
        if (!point.getCalculated()) { point.calc(lastPoint.getChessboard()); }
        if (point.getNextPoints().size() > 0) {
            for (Point p : point.getNextPoints()) {
                handlePoint(layerNum + 1, num, point, p);
            }
        }
    }

    public void doCalc() {
        for (int count = 0; count < num; count++) {
            for (Point point : points) {
                handlePoint(1, num, null, point);
            }
        }
    }

    public static void main(String[] args) {
        int num = 8;
        EightQueens eightQueens = new EightQueens(num);
        eightQueens.doCalc();
    }

}
```



