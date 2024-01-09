import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[][] price;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        price = new int[N][N];
        dp = new int[N][10][1 << N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                price[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(dfs(0,0, 1));
    }

    static int dfs(int owner, int curPrice, int cur) {
        if (dp[owner][curPrice][cur] != 0) {
            return dp[owner][curPrice][cur];
        }

        dp[owner][curPrice][cur] = 1;

        for (int i = 0; i < N; i++) {
            if ((cur & (1 << i)) > 0) {
                continue;
            }

            if (price[owner][i] >= curPrice) {
                dp[owner][curPrice][cur] = Math.max(dp[owner][curPrice][cur],
                        dfs(i, price[owner][i], cur | (1 << i)) + 1);
            }
        }

        return dp[owner][curPrice][cur];
    }
}