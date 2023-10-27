import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1987654321;

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

        // i번 째에서 j번 째 나라에 현재 거주 중인 상태 k는 순회한 나라 기록
        int[][][] dp = new int[N][N][1 << N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        // 어느 도시에서 시작하나 최단 순회 경로는 동일하므로 0번 도시에서 시작
        dp[0][0][1] = 0;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < (1 << N); k++) {
                    for (int l = 1; l < N; l++) {
                        if(cost[j][l] != 0) {
                            dp[i][l][k | (1 << l)] = Math.min(dp[i][l][k | (1 << l)], dp[i-1][j][k] + cost[j][l]);
                        }
                    }
                }
            }
        }

        int min = INF;

        for (int i = 1; i < N; i++) {
            if (cost[i][0] != 0) {
                min = Math.min(min, dp[N-1][i][(1<<N) - 1] + cost[i][0]);
            }
        }

        System.out.println(min);
    }
}