import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n + 1][m + 1];
        boolean[][] visited = new boolean[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        int fr = Integer.parseInt(st.nextToken());
        int fc = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                board[i][j] += board[i][j-1];
            }
        }

        for (int j = 1; j <= m; j++) {
            for (int i = 1; i <= n; i++) {
                board[i][j] += board[i-1][j];
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sr, sc, 0));
        visited[sr][sc] = true;

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (isWall(cur.r, cur.c, cur.r + h - 1, cur.c + w - 1, board)) {
                continue;
            }

            if (cur.r == fr && cur.c == fc) {
                System.out.println(cur.dist);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr <= 0 || nc <= 0 || nr + h - 1 > n || nc + w - 1 > m) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                queue.offer(new Node(nr, nc, cur.dist + 1));
                visited[nr][nc] = true;
            }
        }

        System.out.println(-1);
    }

    static boolean isWall(int r1, int c1, int r2, int c2, int[][] prefix) {
        int wall = prefix[r2][c2] - prefix[r2][c1 - 1] - prefix[r1 - 1][c2] + prefix[r1 - 1][c1 - 1];
        return wall != 0;
    }
}

class Node {
    int r,c,dist;

    public Node(int r, int c, int dist) {
        this.r = r;
        this.c = c;
        this.dist = dist;
    }
}