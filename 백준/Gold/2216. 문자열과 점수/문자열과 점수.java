import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int A;
    static int B;
    static int C;
    static String top;
    static String bot;
    static int topSize;
    static int botSize;
    static int[][] dp;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        top = br.readLine();
        bot = br.readLine();

        topSize = top.length();
        botSize = bot.length();

        dp = new int[topSize + 1][botSize + 1];

        for (int i = 0; i <= topSize; i++) {
            Arrays.fill(dp[i], -INF);
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int topIdx, int botIdx) {
        if (topIdx == topSize && botIdx == botSize) {
            return 0;
        }

        if (dp[topIdx][botIdx] != -INF) {
            return dp[topIdx][botIdx];
        }

        if (topIdx < topSize && botIdx < botSize) {
            int score = top.charAt(topIdx) == bot.charAt(botIdx) ? A : C;
            dp[topIdx][botIdx] = Math.max(dp[topIdx][botIdx], dfs(topIdx + 1, botIdx + 1) + score);
        }

        if (topIdx < topSize) {
            dp[topIdx][botIdx] = Math.max(dp[topIdx][botIdx], dfs(topIdx + 1, botIdx) + B);
        }

        if (botIdx < botSize) {
            dp[topIdx][botIdx] = Math.max(dp[topIdx][botIdx], dfs(topIdx, botIdx + 1) + B);
        }

        return dp[topIdx][botIdx];
    }
}