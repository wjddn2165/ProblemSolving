import java.util.*;

class Solution {
    public int solution(String[] board) {

        int row = board.length;
        int col = board[0].length();

        int[][] map = new int[row][col];
        boolean[][] visited = new boolean[row][col];

        Point src = null;
        Point dest = null;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = board[i].charAt(j);

                if (map[i][j] == 'R') {
                    src = new Point(i, j);
                }

                if (map[i][j] == 'G') {
                    dest = new Point(i, j);
                }
            }
        }

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(src, 0));
        visited[src.r][src.c] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (cur.p.equals(dest)) {
                return cur.depth;
            }

            for (int i = 0; i < 4; i++) {
                Point np = new Point(cur.p);

                while (true) {
                    int nr = np.r + dr[i];
                    int nc = np.c + dc[i];

                    if (nr >= 0 && nc >= 0 && nr < row && nc < col) {
                        if(map[nr][nc] == 'D') {
                            break;
                        }
                    } else {
                        break;
                    }

                    np.r = nr;
                    np.c = nc;
                }

                if (!visited[np.r][np.c]) {
                    queue.offer(new Node(np, cur.depth + 1));
                    visited[np.r][np.c] = true;
                }
            }
        }

        return -1;
    }
}

class Node {
    Point p;
    int depth;

    Node(Point p, int depth) {
        this.p = p;
        this.depth = depth;
    }
}

class Point {
    int r, c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    Point(Point p) {
        this.r = p.r;
        this.c = p.c;
    }

    boolean equals(Point o) {
        return (this.r == o.r && this.c == o.c);
    }
}