import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0; i < N; i++) {
            dp[0][1 << i] = cost[0][i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < (1 << N); j++) {
                if (dp[i - 1][j] != INF) {
                    for (int k = 0; k < N; k++) {
                        if ((j & (1 << k)) == 0) {
                            dp[i][j | (1 << k)] = Math.min(dp[i][j | (1 << k)], dp[i - 1][j] + cost[i][k]);
                        }
                    }
                }
            }
        }
        
        System.out.println(dp[N-1][(1<<N) - 1]);
    }
}