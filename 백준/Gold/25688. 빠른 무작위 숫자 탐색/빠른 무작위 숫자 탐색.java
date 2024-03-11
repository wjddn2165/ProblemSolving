import java.io.*;
import java.util.*;

class Main {

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[5][5];
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int r = 0, c = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        boolean[][][] visited = new boolean[5][5][1 << 6];
        Queue<Node> queue = new LinkedList<>();
        visited[r][c][0] = true;
        queue.offer(new Node(r, c, 0, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (cur.bit == (1 << 6) - 1) {
                System.out.println(cur.count);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5) {
                    continue;
                }

                if (board[nr][nc] == -1) {
                    continue;
                }

                if (board[nr][nc] == 0) {
                    if (!visited[nr][nc][cur.bit]) {
                        queue.offer(new Node(nr, nc, cur.bit, cur.count + 1));
                        visited[nr][nc][cur.bit] = true;
                    }
                } else {
                    int nextBit = cur.bit | (1 << (board[nr][nc] - 1));
                    if (!visited[nr][nc][nextBit]) {
                        queue.offer(new Node(nr, nc, nextBit, cur.count + 1));
                        visited[nr][nc][nextBit] = true;
                    }
                }
            }
        }

        System.out.println(-1);
    }
}

class Node {
    int r, c, bit, count;

    Node(int r, int c, int bit, int count) {
        this.r = r;
        this.c = c;
        this.bit = bit;
        this.count = count;
    }
}