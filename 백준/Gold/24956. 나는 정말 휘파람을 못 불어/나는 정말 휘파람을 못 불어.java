import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        // dp[0] = w 개수, dp[1] wh 개수, dp[2] whe 개수
        int[] dp = new int[3];
        long answer = 0;
        for (int i = 0; i < n; i++) {
            char next = s.charAt(i);
            if (next == 'W') {
                ++dp[0];
                dp[0] %= MOD;
            } else if (next == 'H') {
                dp[1] += dp[0];
                dp[1] %= MOD;
            } else if (next == 'E') {
                answer = (answer * 2 + dp[2]) % MOD;
                dp[2] += dp[1];
                dp[2] %= MOD;
            }
        }

        System.out.println(answer);
    }
}