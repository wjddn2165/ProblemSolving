import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int M;
    static int[][][][] dp;
    static final int MOD = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N][M][2][2];

        System.out.println((dfs(1, 0, 1, 1) + dfs(0, 1, 0, 0)) % MOD);
    }

    static int dfs(int row, int col, int dir, int prev) {
        if (row == N - 1 && col == M - 1) {
            return 1;
        }

        if (row >= N || col >= M) {
            return 0;
        }

        if (dp[row][col][dir][prev] != 0) {
            return Math.max(dp[row][col][dir][prev], 0);
        }

        if (dir == 1) {
            dp[row][col][dir][prev] += dfs(row + 1, col, dir, dir);
            if (prev == 1) {
                dp[row][col][dir][prev] += dfs(row, col + 1, 0, dir);
            }
        } else {
            dp[row][col][dir][prev] += dfs(row, col + 1, dir, dir);
            if (prev == 0) {
                dp[row][col][dir][prev] += dfs(row + 1, col, 1, dir);
            }
        }

        if (dp[row][col][dir][prev] == 0) {
            dp[row][col][dir][prev] = -1;
            return 0;
        }

        return dp[row][col][dir][prev] % MOD;
    }
}