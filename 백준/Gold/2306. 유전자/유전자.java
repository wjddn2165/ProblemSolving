import java.io.*;
import java.util.Arrays;

class Main {

    static String str;
    static int n;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        n = str.length();
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, str.length() - 1));
    }

    static int dfs(int left, int right) {
        if (left >= right) {
            return 0;
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        for (int i = left; i < right; i++) {
            dp[left][right] = Math.max(dp[left][right], dfs(left, i) + dfs(i + 1, right));
        }

        if ((str.charAt(left) == 'a' && str.charAt(right) == 't') || (str.charAt(left) == 'g' && str.charAt(right) == 'c')) {
            dp[left][right] = Math.max(dp[left][right], dfs(left + 1, right - 1) + 2);
        }

        return dp[left][right];
    }
}