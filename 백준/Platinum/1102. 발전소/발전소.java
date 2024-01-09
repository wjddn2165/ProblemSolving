import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] price;
    static int P;
    static int[] dp;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        price = new int[N][N];
        dp = new int[1 << N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                price[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String status = br.readLine();
        P = Integer.parseInt(br.readLine());
        int count = 0;
        int cur = 0;

        for (int i = 0; i < N; i++) {
            if (status.charAt(i) == 'Y') {
                cur |= (1 << i);
                count++;
            }
        }

        // 이미 요구조건이 충족된 경우
        if (count >= P) {
            System.out.println(0);
            return;
        }

        // 모든 발전소가 고장난 경우
        if (cur == 0) {
            System.out.println(-1);
            return;
        }

        Arrays.fill(dp, INF);
        System.out.println(dfs(cur, count));
    }

    static int dfs(int cur, int count) {
        if (count == P) {
            return 0;
        }

        if (dp[cur] != INF) {
            return dp[cur];
        }

        for (int i = 0; i < N; i++) {
            if ((cur & (1 << i)) != 0) {
                continue;
            }

            int minCost = 36;

            for (int j = 0; j < N; j++) {
                if ((cur & (1 << j)) != 0) {
                    minCost = Math.min(minCost, price[j][i]);
                }
            }

            dp[cur] = Math.min(dp[cur], dfs(cur | (1 << i), count + 1) + minCost);
        }

        return dp[cur];
    }
}