import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] board;
    static int[][][] dp;
    static int[] dr = {0, 1, 0};
    static int[] dc = {1, 0, -1};
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        dp = new int[N][M][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i][j], -INF);
            }
        }

        System.out.println(dfs(0, 0, 0));
    }

    static int dfs(int r, int c, int dir) {
        if (r == N - 1 && c == M - 1) {
            return board[r][c];
        }

        if (dp[r][c][dir] != -INF) {
            return dp[r][c][dir];
        }

        for (int i = 0; i < 3; i++) {
            if (dir == 0 && i == 2) continue;
            if (dir == 2 && i == 0) continue;
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                continue;
            }
            dp[r][c][dir] = Math.max(dp[r][c][dir], dfs(nr, nc, i) + board[r][c]);
        }

        return dp[r][c][dir];
    }
}