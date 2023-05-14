import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MOD = 1000000000;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][][] dp = new long[10][N][1 << 10];

        for (int i = 1; i < 10; i++) {
            dp[i][0][1 << i] = 1;
        }

        for (int j = 1; j < N; j++) {
            for (int i = 0; i < 10; i++) {
                for (int k = 0; k < 1024; k++) {
                    if (i > 0) {
                        if (dp[i - 1][j - 1][k] != 0) {
                            dp[i][j][k | (1 << i)] += dp[i - 1][j - 1][k] % MOD;
                        }
                    }

                    if (i < 9) {
                        if (dp[i + 1][j - 1][k] != 0) {
                            dp[i][j][k | (1 << i)] += dp[i + 1][j - 1][k] % MOD;
                        }
                    }
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += dp[i][N - 1][(1<<10) - 1];
        }
        System.out.println(sum % MOD);
    }
}