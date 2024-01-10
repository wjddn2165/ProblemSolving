import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<Integer> top;
    static List<Integer> bot;
    static int topSize;
    static int botSize;
    static int[][][] dp;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        top = new ArrayList<>();
        bot = new ArrayList<>();

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int ti = Integer.parseInt(st1.nextToken());
            int bi = Integer.parseInt(st2.nextToken());

            if (ti != 0) {
                top.add(ti);
            }

            if (bi != 0) {
                bot.add(bi);
            }
        }

        topSize = top.size();
        botSize = bot.size();

        dp = new int[N][topSize + 1][botSize + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < topSize; j++) {
                Arrays.fill(dp[i][j], -INF);
            }
        }

        System.out.println(dfs(0, 0, 0));
    }

    static int dfs(int idx, int topIdx, int botIdx) {
        if (idx == N) {
            return 0;
        }

        if (dp[idx][topIdx][botIdx] != -INF) {
            return dp[idx][topIdx][botIdx];
        }

        // 위 숫자만 사용
        if (topIdx < topSize && idx - botIdx < N - botSize) {
            dp[idx][topIdx][botIdx] = Math.max(dp[idx][topIdx][botIdx], dfs(idx + 1, topIdx + 1, botIdx));
        }

        // 아래 숫자만 사용
        if (botIdx < botSize && idx - topIdx < N - topSize) {
            dp[idx][topIdx][botIdx] = Math.max(dp[idx][topIdx][botIdx], dfs(idx + 1, topIdx, botIdx + 1));
        }

        // 동시에 사용
        if (topIdx < topSize && botIdx < botSize) {
            dp[idx][topIdx][botIdx] = Math.max(dp[idx][topIdx][botIdx], dfs(idx + 1, topIdx + 1, botIdx + 1) + (top.get(topIdx) * bot.get(botIdx)));
        }

        // 둘다 사용 x
        if (idx - botIdx < N - botSize && idx - topIdx < N - topSize) {
            dp[idx][topIdx][botIdx] = Math.max(dp[idx][topIdx][botIdx], dfs(idx + 1, topIdx, botIdx));
        }

        return dp[idx][topIdx][botIdx];
    }
}