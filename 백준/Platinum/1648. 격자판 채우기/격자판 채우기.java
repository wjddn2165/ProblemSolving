import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int M;
    static int[][] dp;
    static final int MOD = 9901;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if ((N * M) % 2 == 1) {
            System.out.println(0);
            return;
        }

        dp = new int[N * M + 1][1 << M];
        for (int i = 0; i <= N * M; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int k, int s) {
        if (k == N * M && s == 0) {
            return 1;
        }

        if (k > N * M) {
            return 0;
        }

        if (dp[k][s] != -1) {
            return dp[k][s];
        }

        dp[k][s] = 0;

        // 현재 칸을 채울 수 없는 경우
        if ((s & 1) == 1) {
            dp[k][s] += dfs(k + 1, s >> 1);
        }
        // 오른쪽으로 채우기
        else {
            if ((k % M) != M - 1 && (s & 2) == 0) {
                dp[k][s] += dfs(k + 2, s >> 2);
            }

            // 아래로 채우기
            dp[k][s] += dfs(k + 1, (s >> 1) | (1 << M - 1));
        }

        return dp[k][s] % MOD;
    }
}