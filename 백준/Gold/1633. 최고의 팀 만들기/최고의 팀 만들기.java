import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static List<int[]> power;
    static int[][][] dp;
    static int N;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        power = new ArrayList<>();

        while ((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            int black = Integer.parseInt(st.nextToken());
            int white = Integer.parseInt(st.nextToken());
            power.add(new int[]{black, white});
        }

        N = power.size();
        dp = new int[N][16][16];

        System.out.println(dfs(0, 0, 0));
    }

    static int dfs(int idx, int black, int white) {
        if (black > 15 || white > 15) {
            return -INF;
        }

        if (black == 15 && white == 15) {
            return 0;
        }

        if (idx == N) {
            return -INF;
        }

        if (dp[idx][black][white] != 0) {
            return dp[idx][black][white];
        }

        // 선택 하는 경우
        dp[idx][black][white] = Math.max(dfs(idx + 1, black + 1, white) + power.get(idx)[0],
                dfs(idx + 1, black, white + 1) + power.get(idx)[1]);

        // 선택 안하는 경우
        dp[idx][black][white] = Math.max(dp[idx][black][white], dfs(idx + 1, black, white));

        return dp[idx][black][white];
    }
}