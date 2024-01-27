import java.io.*;
import java.util.*;

class Main {

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][][] dist = new int[N][N][3];
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(dist[i][j], INF);
            }
        }

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        PriorityQueue<Node> queue = new PriorityQueue<>();
        dist[0][0][0] = 0;
        queue.offer(new Node(0, 0, 0, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (cur.time > dist[cur.r][cur.c][cur.count]) {
                continue;
            }

            if (cur.r == N - 1 && cur.c == N - 1) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }

                int sum = cur.time + T;
                if (cur.count == 2) {
                    sum += board[nr][nc];
                }

                if (sum < dist[nr][nc][(cur.count + 1) % 3]) {
                    dist[nr][nc][(cur.count + 1) % 3] = sum;
                    queue.offer(new Node(nr, nc, sum, (cur.count + 1) % 3));
                }
            }
        }

        int answer = INF;
        
        for (int i = 0; i < 3; i++) {
            answer = Math.min(answer, dist[N - 1][N - 1][i]);
        }
        
        System.out.println(answer);
    }
}

class Node implements Comparable<Node> {
    int r, c, time, count;

    Node(int r, int c, int time, int count) {
        this.r = r;
        this.c = c;
        this.time = time;
        this.count = count;
    }

    @Override
    public int compareTo(Node n) {
        return this.time - n.time;
    }
}