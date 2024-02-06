import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int K;
    static int[] light;
    static int[][] dp;
    static final int INF = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        light = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            light[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }

        System.out.println(dfs(0, N - 1));
    }

    static int dfs(int left, int right) {
        if (left == right) {
            return 0;
        }

        if (dp[left][right] != INF) {
            return dp[left][right];
        }

        for (int i = left; i < right; i++) {
            dp[left][right] = Math.min(dp[left][right], dfs(left, i) + dfs(i + 1, right) + (light[left] == light[i + 1] ? 0 : 1));
        }

        return dp[left][right];
    }
}