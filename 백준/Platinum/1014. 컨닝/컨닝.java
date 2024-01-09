import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int[][] dp;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dp = new int[N][1 << M];
            board = new int[N][M];

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                Arrays.fill(dp[i], -1);
                for (int j = 0; j < M; j++) {
                    board[i][j] = str.charAt(j);
                }
            }

            answer.append(dfs(0, 0)).append("\n");
        }

        System.out.print(answer);
    }

    static int dfs(int row, int visited) {
        if (row == N) {
            return 0;
        }

        if (dp[row][visited] != -1) {
            return dp[row][visited];
        }

        dp[row][visited] = 0;

        outer: for (int cur = 0; cur < (1 << M); cur++) {
            int count = 0;
            for (int i = 0; i < M; i++) {
                int bit = cur & (1 << i);

                if (bit == 0) {
                    continue;
                }

                count++;

                if (board[row][i] == 'x') {
                    continue outer;
                }

                if (i > 0 && (visited & (1 << (i - 1))) != 0) {
                    continue outer;
                }

                if (i < (M - 1) && (visited & (1 << (i + 1))) != 0) {
                    continue outer;
                }

                if (i > 0 && (cur & (1 << (i - 1))) > 0) {
                    continue outer;
                }
            }

            dp[row][visited] = Math.max(dp[row][visited], dfs(row + 1, cur) + count);
        }

        return dp[row][visited];
    }
}