import java.io.*;
import java.util.*;

class Main {

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> queue = new PriorityQueue<>();
        int[][][] dist = new int[H][W][5];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }

        int[][] board = new int[H][W];
        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (board[i][j] == 'C' && queue.isEmpty()) {
                    for (int k = 0; k < 5; k++) {
                        dist[i][j][k] = -1;
                    }

                    queue.offer(new Node(i, j, 4, -1));
                }
            }
        }

        int answer = INF;

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (cur.count > dist[cur.r][cur.c][cur.dir]) {
                continue;
            }

            if (board[cur.r][cur.c] == 'C' && dist[cur.r][cur.c][cur.dir] != -1) {
//                System.out.println(Arrays.toString(dist[cur.r][cur.c]));
//                System.out.println(cur.r + " " + cur.c + " " + cur.dir);
                answer = Math.min(answer, cur.count);
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= H || nc >= W) {
                    continue;
                }

                if (board[nr][nc] == '*') {
                    continue;
                }

                if (i == cur.dir) {
                    if (dist[nr][nc][cur.dir] >= cur.count) {
                        dist[nr][nc][cur.dir] = cur.count;
                        queue.offer(new Node(nr, nc, i, cur.count));
                    }
                } else {
                    if (dist[nr][nc][cur.dir] > cur.count + 1) {
                        dist[nr][nc][cur.dir] = cur.count + 1;
                        queue.offer(new Node(nr, nc, i, cur.count + 1));
                    }
                }
            }
        }

        System.out.println(answer);
    }
}

class Node implements Comparable<Node> {
    int r, c, dir, count;

    Node(int r, int c, int dir, int count) {
        this.r = r;
        this.c = c;
        this.dir = dir;
        this.count = count;
    }

    @Override
    public int compareTo(Node o) {
        return count - o.count;
    }
}