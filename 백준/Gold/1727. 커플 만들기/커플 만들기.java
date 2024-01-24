import java.io.*;
import java.util.*;

class Main {

    static int n;
    static int m;
    static int[][] dp;
    static int[] man;
    static int[] woman;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        man = new int[n];
        woman = new int[m];
        dp = new int[n + 1][m + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            man[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            woman[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(man);
        Arrays.sort(woman);

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int manIdx, int womanIdx) {
        if (n <= m) {
            if (manIdx < n && womanIdx == m) {
                return INF + 1;
            } else if (manIdx == n) {
                return 0;
            }
        }

        if (n > m) {
            if (womanIdx < m && manIdx == n) {
                return INF + 1;
            } else if (womanIdx == m) {
                return 0;
            }
        }

        if (dp[manIdx][womanIdx] != INF) {
            return dp[manIdx][womanIdx];
        }

        if (n <= m) {
            dp[manIdx][womanIdx] = Math.min(dfs(manIdx, womanIdx + 1),
                    dfs(manIdx + 1, womanIdx + 1) + Math.abs(man[manIdx] - woman[womanIdx]));
        } else {
            dp[manIdx][womanIdx] = Math.min(dfs(manIdx + 1, womanIdx),
                    dfs(manIdx + 1, womanIdx + 1) + Math.abs(man[manIdx] - woman[womanIdx]));        }


        return dp[manIdx][womanIdx];
    }
}