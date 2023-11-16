import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        int[] dp = new int[N];

        int max = 1;

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            dp[i] = 1;
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (nums[i] < nums[j]) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                    max = Math.max(max, dp[j]);
                }
            }
        }

        System.out.println(N - max);
    }
}