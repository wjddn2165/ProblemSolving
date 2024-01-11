import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int M;
    static int[][] dp;
    static char[][] board;
    static final int[][] price = {
            {10, 8, 7, 5, 1},
            {8, 6, 4, 3, 1},
            {7, 4, 3, 2, 1},
            {5, 3, 2, 2, 1},
            {1, 1, 1, 1, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        dp = new int[N * M + 1][1 << M];
        for (int i = 0; i <= N * M; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int k, int s) {
        if (k == N * M) {
            return 0;
        }

        if (dp[k][s] != -1) {
            return dp[k][s];
        }

        dp[k][s] = 0;

        // 현재 두부를 버리는 경우
        dp[k][s] = Math.max(dp[k][s], dfs(k + 1, s >> 1));

        // 두부를 오른쪽으로 2개 선택
        if ((k % M) != M - 1 && (s & 1) == 0 && (s & 2) == 0) {
            dp[k][s] = Math.max(dp[k][s], dfs(k + 2, s >> 2) + getPrice(k, k + 1));
        }

        // 두부를 아래로 2개 선택
        if ((s & 1) == 0 && k + M < N * M) {
            dp[k][s] = Math.max(dp[k][s], dfs(k + 1, (s >> 1) | (1 << (M - 1))) + getPrice(k, k + M));
        }

        return dp[k][s];
    }

    static int getPrice(int pos1, int pos2) {
        char ch1 = board[pos1 / M][pos1 % M];
        char ch2 = board[pos2 / M][pos2 % M];

        return price[getIndex(ch1)][getIndex(ch2)];
    }

    static int getIndex(char ch) {
        if (ch == 'F') {
            return 4;
        }

        return ch - 'A';
    }
}