import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int K;
    static int[][] value;
    static int[][][] dp;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int sum = 0;

        value = new int[N][2];
        dp = new int[N][K][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            value[i][0] = Integer.parseInt(st.nextToken());
            value[i][1] = Integer.parseInt(st.nextToken());

            sum += value[i][0] + value[i][1];
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        // 마지막 입력 버림
        br.readLine();
        System.out.println(sum - dfs(0, 0, 0));
    }

    // prev = 0 전에 선택 x
    // prev = 1 전에 왼쪽 선택
    // prev = 2 전에 오른쪽 선택
    static int dfs(int idx, int count, int prev) {
        if (idx == N) {
            if (count < K) {
                return INF + 1;
            }
            return 0;
        }

        if (count == K) {
            return 0;
        }

        if (dp[idx][count][prev] != INF) {
            return dp[idx][count][prev];
        }

        dp[idx][count][prev] = INF + 1;

        // 오른쪽 선택
        if (prev == 0 || prev == 2) {
            dp[idx][count][prev] = Math.min(dp[idx][count][prev], dfs(idx + 1, count + 1, 2) + value[idx][1]);
        }

        // 왼쪽 선택
        if (prev == 0 || prev == 1) {
            dp[idx][count][prev] = Math.min(dp[idx][count][prev], dfs(idx + 1, count + 1, 1) + value[idx][0]);
        }

        // 선택 x
        dp[idx][count][prev] = Math.min(dp[idx][count][prev], dfs(idx + 1, count, 0));

        return dp[idx][count][prev];
    }
}