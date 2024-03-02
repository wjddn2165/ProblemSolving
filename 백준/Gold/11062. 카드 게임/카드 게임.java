import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dp = new int[N][N];

            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], -1);
            }

            answer.append(dfs(0, N - 1, 1)).append("\n");
        }

        System.out.print(answer);
    }

    static int dfs(int left, int right, int turn) {
        if (left == right) {
            if (turn % 2 == 1) {
                return arr[left];
            }

            return 0;
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        // 근우
        if (turn % 2 == 1) {
            return dp[left][right] = Math.max(dfs(left + 1, right, turn + 1) + arr[left], dfs(left, right - 1, turn + 1) + arr[right]);
        }

        // 명우
        return dp[left][right] = Math.min(dfs(left + 1, right, turn + 1), dfs(left, right - 1, turn + 1));
    }
}