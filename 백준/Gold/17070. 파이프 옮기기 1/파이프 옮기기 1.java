import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][][][] dp;
    static int N;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][N][N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < N; l++) {
                        dp[i][j][k][l] = -1;
                    }
                }
            }
        }

        System.out.println(dfs(0, 0, 0, 1));
    }

    static int dfs(int r1, int c1, int r2, int c2) {
        if (r1 < 0 || r2 < 0 || c1 < 0 || c2 < 0 || r1 >= N || r2 >= N || c1 >= N || c2 >= N) {
            return 0;
        }

        // 대각선이 아닌 경우
        if ((r1 == r2 || c1 == c2) && (board[r1][c1] == 1 || board[r2][c2] == 1)) {
            return 0;
        }

        if ((r1 != r2 && c1 != c2 && (board[r1][c1] == 1 || board[r2][c2] == 1 || board[r1][c2] == 1 || board[r2][c1] == 1))) {
            return 0;
        }
        
        if (dp[r1][c1][r2][c2] != -1) {
            return dp[r1][c1][r2][c2];
        }

        if (r2 == N - 1 && c2 == N - 1) {
            return dp[r1][c1][r2][c2] = 1;
        }

        dp[r1][c1][r2][c2] = 0;

        // 가로
        if (r1 == r2) {
            dp[r1][c1][r2][c2] += dfs(r1, c1 + 1, r2, c2 + 1);
            dp[r1][c1][r2][c2] += dfs(r1, c1 + 1, r2 + 1, c2 + 1);
        }

        // 새로
        else if (c1 == c2) {
            dp[r1][c1][r2][c2] += dfs(r1 + 1, c1, r2 + 1, c2);
            dp[r1][c1][r2][c2] += dfs(r1 + 1, c1, r2 + 1, c2 + 1);
        }

        // 대각선
        else {
            dp[r1][c1][r2][c2] += dfs(r1 + 1, c1 + 1, r2, c2 + 1);
            dp[r1][c1][r2][c2] += dfs(r1 + 1, c1 + 1, r2 + 1, c2);
            dp[r1][c1][r2][c2] += dfs(r1 + 1, c1 + 1, r2 + 1, c2 + 1);
        }

        return dp[r1][c1][r2][c2];
    }
}