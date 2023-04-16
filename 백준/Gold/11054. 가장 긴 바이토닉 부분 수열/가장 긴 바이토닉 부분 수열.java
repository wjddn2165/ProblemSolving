import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][2];

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (nums[j] > nums[i]) {
                    dp[j][0] = Math.max(dp[j][0], dp[i][0] + 1);
                }
            }
        }

        for (int i = N - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] < nums[j]) {
                    dp[j][1] = Math.max(dp[j][1], dp[i][1] + 1);
                }
            }
        }

        int max = 0;

        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i][0] + dp[i][1]);
        }

        System.out.println(max + 1);
    }
}