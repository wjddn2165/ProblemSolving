import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] w = new int[N];
        int[] c = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][x + 1];

        for (int i = 0; i < N; i++) {
            int cnt = 0;
            dp[i][0] = 1;
            for (int j = 0; j <= x; j++) {
                dp[i + 1][j] = dp[i][j];
            }
            while (cnt++ < c[i]) {
                for (int j = x; j >= cnt * w[i]; j--) {
                    if (j >= cnt * w[i]) {
                        dp[i + 1][j] += dp[i][j - cnt * w[i]];
                    }
                }
            }
        }

        System.out.println(dp[N][x]);
    }
}