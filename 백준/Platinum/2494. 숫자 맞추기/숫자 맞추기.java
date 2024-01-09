import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static String src;
    static String dest;
    static int[][] dp;
    static final int INF = 987654321;
    static int[] trace;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        src = br.readLine();
        dest = br.readLine();
        dp = new int[N][10];
        trace = new int[N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }

        System.out.println(dfs(0, 0));
        find(0, 0);
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            answer.append(i + 1).append(" ").append(trace[i]).append("\n");
        }

        System.out.print(answer);
    }

    static int dfs(int idx, int curl) {
        if (idx == N) {
            return 0;
        }

        if (dp[idx][curl] != INF) {
            return dp[idx][curl];
        }

        int cur = src.charAt(idx) - '0';
        int target = dest.charAt(idx) - '0';

        cur = (cur + curl) % 10;

        if (cur == target) {
            dp[idx][curl] = Math.min(dp[idx][curl], dfs(idx + 1, curl));
        } else {
            // 현재 숫자나사를 오른쪽으로 회전
            dp[idx][curl] = Math.min(dp[idx][curl], dfs(idx + 1, curl) + ((cur - target) + 10) % 10);

            int count = ((target - cur) + 10) % 10;
            // 현재 숫자나사를 왼쪽으로 회전
            dp[idx][curl] = Math.min(dp[idx][curl], dfs(idx + 1, (curl + count) % 10) + count);
        }

        return dp[idx][curl];
    }

    static void find(int idx, int curl) {
        if (idx == N) {
            return;
        }
        int cur = src.charAt(idx) - '0';
        int target = dest.charAt(idx) - '0';

        cur = (cur + curl) % 10;

        if (cur == target) {
            find(idx + 1, curl);
        } else {

            int rightCount = ((cur - target) + 10) % 10;
            int leftCount = ((target - cur) + 10) % 10;

            if (idx == N - 1) {
                if (rightCount < leftCount) {
                    trace[idx] = -rightCount;
                } else {
                    trace[idx] = leftCount;
                }

                return;
            }

            if (dp[idx + 1][curl] + rightCount < dp[idx + 1][(curl + leftCount) % 10] + leftCount) {
                trace[idx] = -rightCount;
                find(idx + 1, curl);
            } else {
                trace[idx] = leftCount;
                find(idx + 1, (curl + leftCount) % 10);
            }
        }
    }
}