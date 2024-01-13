import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] count;
    static long[][][][][][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        count = new int[5];

        for (int i = 0; i < N; i++) {
            count[i] = Integer.parseInt(br.readLine());
        }

        dp = new long[N + 1][N + 1][11][11][11][11][11];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                for (int k = 0; k < 11; k++) {
                    for (int l = 0; l < 11; l++) {
                        for (int o = 0; o < 11; o++) {
                            for (int p = 0; p < 11; p++) {
                                Arrays.fill(dp[i][j][k][l][o][p], -1);
                            }
                        }
                    }
                }
            }
        }

        System.out.println(dfs(0, 0, count[0], count[1], count[2], count[3], count[4]));
    }

    static long dfs(int prev2, int prev, int c1, int c2, int c3, int c4, int c5) {
        if (c1 + c2 + c3 + c4 + c5 == 0) {
            return 1;
        }

        if (dp[prev2][prev][c1][c2][c3][c4][c5] != -1) {
            return dp[prev2][prev][c1][c2][c3][c4][c5];
        }

        dp[prev2][prev][c1][c2][c3][c4][c5] = 0;

        long sum = 0;

        for (int i = 1; i <= N; i++) {
            if (prev2 == i || prev == i) {
                continue;
            }

            if (i == 1 && c1 == 0) continue;
            if (i == 2 && c2 == 0) continue;
            if (i == 3 && c3 == 0) continue;
            if (i == 4 && c4 == 0) continue;
            if (i == 5 && c5 == 0) continue;

            if (i == 1) sum += dfs(prev, 1, c1 - 1, c2, c3, c4, c5);
            if (i == 2) sum += dfs(prev, 2, c1, c2 - 1, c3, c4, c5);
            if (i == 3) sum += dfs(prev, 3, c1, c2, c3 - 1, c4, c5);
            if (i == 4) sum += dfs(prev, 4, c1, c2, c3, c4 - 1, c5);
            if (i == 5) sum += dfs(prev, 5, c1, c2, c3, c4, c5 - 1);
        }

        return dp[prev2][prev][c1][c2][c3][c4][c5] = sum;
    }
}