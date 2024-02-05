import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int[] height;
    static long[][] dp;
    static final long INF = 100_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        height = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        dp = new long[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], INF);
        }

        System.out.println(dfs(0, 0, 1));
    }

    static long dfs(int left, int right, int idx) {
        if (idx == N + 1) {
            return 0;
        }

        if (dp[left][right] != INF) {
            return dp[left][right];
        }

        dp[left][right] = INF + 1;

        // 희원이가 부르는 경우
        if (left == 0) {
            dp[left][right] = dfs(idx, right, idx + 1);
        } else {
            dp[left][right] = dfs(idx, right, idx + 1) + Math.abs(height[left - 1] - height[idx - 1]);
        }
        // 상덕이가 부르는 경우
        if (right == 0) {
            dp[left][right] = Math.min(dp[left][right], dfs(left, idx, idx + 1));
        } else {
            dp[left][right] = Math.min(dp[left][right], dfs(left, idx, idx + 1) + Math.abs(height[right - 1] - height[idx - 1]));
        }

        return dp[left][right];
    }
}