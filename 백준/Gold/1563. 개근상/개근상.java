import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MOD = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][][] dp = new int[n + 1][2][3];

        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;

        for (int i = 2; i <= n; i++) {
            // 출석한 경우
            dp[i][0][0] += dp[i - 1][0][0] % MOD + dp[i - 1][0][1] % MOD + dp[i - 1][0][2] % MOD;
            dp[i][1][0] += dp[i - 1][1][0] % MOD + dp[i - 1][1][1] % MOD + dp[i - 1][1][2] % MOD;
            // 지각한 경우
            dp[i][1][0] += dp[i - 1][0][0] % MOD + dp[i - 1][0][1] % MOD + dp[i - 1][0][2] % MOD;
            // 결석한 경우
            dp[i][0][1] = dp[i - 1][0][0] % MOD;
            dp[i][0][2] = dp[i - 1][0][1] % MOD;
            dp[i][1][1] = dp[i - 1][1][0] % MOD;
            dp[i][1][2] = dp[i - 1][1][1] % MOD;
        }

        int sum = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sum += dp[n][i][j] % MOD;
            }
        }

        System.out.println(sum % MOD);
    }
}