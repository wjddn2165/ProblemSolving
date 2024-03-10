import java.io.*;
import java.util.*;

class Main {

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        char totalCount = 0;

        int sr = 0, sc = 0, er = 0, ec = 0;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == 'S') {
                    board[i][j] = '.';
                    sr = i;
                    sc = j;
                }

                if (board[i][j] == 'E') {
                    er = i;
                    ec = j;
                }

                if (board[i][j] == 'X') {
                    board[i][j] = totalCount++;
                }
            }
        }

        boolean[][][] visited = new boolean[N][M][1 << totalCount];
        Queue<Node> queue = new LinkedList<>();
        visited[sr][sc][0] = true;
        queue.offer(new Node(sr, sc, 0, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (cur.r == er && cur.c == ec && cur.bit == (1 << totalCount) - 1) {
                System.out.println(cur.count);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }

                if (board[nr][nc] == '#') {
                    continue;
                }

                if (board[nr][nc] == '.' || board[nr][nc] == 'E') {
                    if (!visited[nr][nc][cur.bit]) {
                        queue.offer(new Node(nr, nc, cur.bit, cur.count + 1));
                        visited[nr][nc][cur.bit] = true;
                    }

                    continue;
                }
                
                int flag = board[nr][nc];
                int nextBit = cur.bit | (1 << flag);

                if (!visited[nr][nc][nextBit]) {
                    queue.offer(new Node(nr, nc, nextBit, cur.count + 1));
                    visited[nr][nc][nextBit] = true;
                }
            }
        }
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