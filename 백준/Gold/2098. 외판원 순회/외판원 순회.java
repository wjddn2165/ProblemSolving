import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    static int N;
    static int[][] cost;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // i 번째 도시에서 현재 방문한 도시들 상태에서 다시 0번 도시로 돌아갈 때 드는 최소 비용
        dp = new int[N][(1 << (N - 1))];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int cur, int visited) {
        // 모든 도시 방문
        if(visited == (1 << (N - 1)) - 1) {
            if (cost[cur][0] == 0) {
                return INF;
            } else {
                return cost[cur][0];
            }
        }

        if (dp[cur][visited] != -1) {
            return dp[cur][visited];
        }

        dp[cur][visited] = INF;

        // 현재 방문을 하지 않은 도시들에 대해 방문
        for (int i = 0; i < N - 1; i++) {
            if ((visited & (1 << i)) == 0 && cost[cur][i + 1] != 0) {
                dp[cur][visited] = Math.min(dp[cur][visited], dfs(i + 1, visited | (1 << i)) + cost[cur][i + 1]);
            }
        }

        return dp[cur][visited];
    }
}