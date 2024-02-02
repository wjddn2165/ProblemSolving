import java.io.*;
import java.util.*;

public class Main {

    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N;
    static int[][] board;
    static boolean[][] visited;
    static int[][] height;
    static int delivery = 0;
    static int sr, sc;
    static Set<Integer> heightSet = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        height = new int[N][N];

        int minHeight = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'K') {
                    delivery++;
                }

                if (board[i][j] == 'P') {
                    sr = i;
                    sc = j;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                height[i][j] = Integer.parseInt(st.nextToken());
                heightSet.add(height[i][j]);

                if (board[i][j] == 'P' || board[i][j] == 'K') {
                    minHeight = Math.min(minHeight, height[i][j]);
                }
            }
        }

        List<Integer> boundary = new ArrayList<>(heightSet);
        boundary.sort(null);

        int lo = 0;
        int hi = 0;

        int minIdx = boundary.indexOf(minHeight);
        int answer = Integer.MAX_VALUE;

        while (hi < N * N && lo <= minIdx) {
            if (bfs(boundary.get(lo), boundary.get(hi))) {
                answer = Math.min(answer, boundary.get(hi) - boundary.get(lo));
                lo++;
            } else {
                hi++;
            }
        }

        System.out.println(answer);
    }

    static boolean bfs(int minHeight, int maxHeight) {
        if (minHeight > height[sr][sc] || height[sr][sc] > maxHeight) {
            return false;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sr, sc));
        visited = new boolean[N][N];
        visited[sr][sc] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (count == delivery) {
                return true;
            }

            for (int i = 0; i < 8; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }

                if (height[nr][nc] < minHeight || height[nr][nc] > maxHeight) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                if (board[nr][nc] == 'K') {
                    count++;
                }

                visited[nr][nc] = true;
                queue.offer(new Node(nr, nc));
            }
        }

        return false;
    }

}

class Node {
    int r, c;

    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}