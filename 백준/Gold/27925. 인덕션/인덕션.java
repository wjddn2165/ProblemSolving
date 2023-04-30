import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] food = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            food[i] = Integer.parseInt(st.nextToken());
        }

        int[][][][] dp = new int[N + 1][10][10][10];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        dp[i][j][k][l] = INF;
                    }
                }
            }
        }

        dp[0][0][0][0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {

                        dp[i + 1][food[i]][k][l]
                                = Math.min(dp[i + 1][food[i]][k][l], dp[i][j][k][l] + Math.min(Math.abs(j - food[i]), Math.min(j + 10 - food[i], food[i] + 10 - j)));

                        dp[i + 1][j][food[i]][l]
                                = Math.min(dp[i + 1][j][food[i]][l], dp[i][j][k][l] + Math.min(Math.abs(k - food[i]), Math.min(k + 10 - food[i], food[i] + 10 - k)));

                        dp[i + 1][j][k][food[i]]
                                = Math.min(dp[i + 1][j][k][food[i]], dp[i][j][k][l] + Math.min(Math.abs(l - food[i]), Math.min(l + 10 - food[i], food[i] + 10 - l)));
                    }
                }
            }
        }

        int result = INF;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    result = Math.min(result, dp[N][i][j][k]);
                }
            }
        }

        System.out.println(result);
    }
}