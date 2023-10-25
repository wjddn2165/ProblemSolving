import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 987654321;
    static int r;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[][] cost = new int[r][c];
        int[][][] dp = new int[r][c][3];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }

        // 초기화 작업
        for (int i = 0; i < c - 1; i++) {
            dp[1][i + 1][2] = cost[0][i] + cost[1][i + 1];
        }

        for (int i = 0; i < c; i++) {
            dp[1][i][1] = cost[0][i] + cost[1][i];
        }

        for (int i = 1; i < c; i++) {
            dp[1][i - 1][0] = cost[0][i] + cost[1][i - 1];
        }

        // 점화식
        for (int i = 1; i < r - 1; i++) {
            for (int j = 0; j < c - 1; j++) {
                dp[i + 1][j + 1][2] = Math.min(dp[i + 1][j + 1][2], Math.min(dp[i][j][0], dp[i][j][1]) + cost[i + 1][j + 1]);
            }

            for (int j = 0; j < c; j++) {
                dp[i + 1][j][1] = Math.min(dp[i + 1][j][1], Math.min(dp[i][j][0], dp[i][j][2]) + cost[i + 1][j]);
            }

            for (int j = 1; j < c; j++) {
                dp[i + 1][j - 1][0] = Math.min(dp[i + 1][j - 1][0], Math.min(dp[i][j][1], dp[i][j][2]) + cost[i + 1][j - 1]);
            }
        }

        int min = INF;

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < 3; j++) {
                min = Math.min(dp[r - 1][i][j], min);
            }
        }

        System.out.println(min);
    }
}