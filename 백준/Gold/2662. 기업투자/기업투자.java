import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] cost = new int[M][N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int money = Integer.parseInt(st.nextToken());
            for (int j = 0; j < M; j++) {
                cost[j][money] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[M][N + 1];
        int[][][] invest = new int[M][N + 1][M];

        for (int i = 1; i <= N; i++) {
            dp[0][i] = cost[0][i];
            invest[0][i][0] = i;
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 0; k <= j; k++) {
                    if (dp[i][j] < dp[i - 1][k] + cost[i][j - k]) {
                        dp[i][j] = dp[i - 1][k] + cost[i][j - k];
                        for (int l = 0; l < M; l++) {
                            invest[i][j][l] = invest[i - 1][k][l];
                        }
                        invest[i][j][i] += j - k;
                    }
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append(dp[M - 1][N]).append("\n");
        for (int i = 0; i < M; i++) {
            answer.append(invest[M - 1][N][i]).append(" ");
        }

        System.out.println(answer);
    }
}