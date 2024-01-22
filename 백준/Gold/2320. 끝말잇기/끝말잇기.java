import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int[][] dp;
    static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][1 << N];
        words = new String[N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
            words[i] = br.readLine();
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, dfs(i, 1 << i) + words[i].length());
        }

        System.out.println(answer);
    }

    static int dfs(int idx, int bit) {
        if (bit == (1 << N) - 1) {
            return 0;
        }

        if (dp[idx][bit] != -1) {
            return dp[idx][bit];
        }

        String cur = words[idx];
        char last = cur.charAt(cur.length() - 1);

        dp[idx][bit] = 0;

        for (int i = 0; i < N; i++) {
            if (((1 << i) & bit) > 0) {
                continue;
            }

            if (words[i].charAt(0) != last) {
                continue;
            }

            dp[idx][bit] = Math.max(dp[idx][bit], dfs(i, bit | (1 << i)) + words[i].length());
        }

        return dp[idx][bit];
    }
}