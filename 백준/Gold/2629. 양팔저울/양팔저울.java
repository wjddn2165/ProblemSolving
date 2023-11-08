import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] w = new int[n];
        int maxW = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            w[i] = Integer.parseInt(st.nextToken());
            maxW += w[i];
        }
        boolean[][] dp = new boolean[n + 1][maxW + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= maxW; j++) {
                dp[i + 1][j] = dp[i + 1][j] || dp[i][j];

                if (j - w[i] >= 0) {
                    dp[i + 1][j] = dp[i + 1][j] || dp[i][j - w[i]];
                }

                if (j + w[i] <= maxW) {
                    dp[i + 1][j] = dp[i + 1][j] || dp[i][j + w[i]];
                }

                if (w[i] - j >= 0) {
                    dp[i + 1][j] = dp[i + 1][j] || dp[i][w[i] - j];
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int next = Integer.parseInt(st.nextToken());
            if (next > maxW || !dp[n][next]) {
                answer.append("N").append(" ");
            } else {
                answer.append("Y").append(" ");
            }
        }

        System.out.println(answer);
    }
}