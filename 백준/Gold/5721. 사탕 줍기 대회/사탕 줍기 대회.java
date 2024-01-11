import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int M;
    static int[][] board;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            board = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp = new int[N];
            Arrays.fill(dp, -1);

            answer.append(dfs(0)).append("\n");
        }

        System.out.print(answer);
    }

    static int dfs(int row) {
        if (row >= N) {
            return 0;
        }

        if (dp[row] != -1) {
            return dp[row];
        }

        // 현재 행 선택
        int[] tmp = new int[M + 1];
        tmp[1] = board[row][0];
        for (int i = 2; i <= M; i++) {
            tmp[i] = Math.max(tmp[i - 1], tmp[i - 2] + board[row][i - 1]);
        }

        int max = tmp[M];

        dp[row] = Math.max(dfs(row + 1), dfs(row + 2) + max);

        return dp[row];
    }
}