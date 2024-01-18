import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] nums;
    static int[][] dp;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        dp = new int[N][N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], INF);
        }

        System.out.println(dfs(0, N - 1));
    }

    static int dfs(int left, int right) {
        if (left >= right) {
            return 0;
        }

        if (dp[left][right] != INF) {
            return dp[left][right];
        }

        if (nums[left] == nums[right]) {
            dp[left][right] = dfs(left + 1, right - 1);
        } else {
            dp[left][right] = Math.min(dfs(left + 1, right), dfs(left, right - 1)) + 1;
        }

        return dp[left][right];
    }
}