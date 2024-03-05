import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] score;
    static long[][][] dp;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        score = new int[N][4];
        dp = new long[N][12][1 << 4];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= 11; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(dfs(0, 0, 0));
    }

    static long dfs(int idx, int count, int bit) {
        if (count == 11) {
            if (bit == (1 << 4) - 1) {
                return 0;
            }

            return Long.MIN_VALUE;
        }

        if (idx == N) {
            return 0;
        }

        if (dp[idx][count][bit] != -1) {
            return dp[idx][count][bit];
        }

        dp[idx][count][bit] = dfs(idx + 1, count, bit);

        for (int i = 0; i < 3; i++) {
            dp[idx][count][bit] = Math.max(dp[idx][count][bit], dfs(
                    idx + 1, count + 1, bit | (1 << i)
            ) + score[idx][i]);
        }

        if (((1 << 3) & bit) == 0) {
            dp[idx][count][bit] = Math.max(dp[idx][count][bit], dfs(
                    idx + 1, count + 1, bit | (1 << 3)
            ) + score[idx][3]);
        }

        return dp[idx][count][bit];
    }
}