import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int[][] dp;
    static char[][] board;
    static final int[][] price = {
            {100, 70, 40, 0},
            {70, 50, 30, 0},
            {40, 30, 20, 0},
            {0, 0, 0, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        dp = new int[N * N + 1][1 << N];
        for (int i = 0; i <= N * N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int k, int s) {
        if (k == N * N) {
            return 0;
        }

        if (dp[k][s] != -1) {
            return dp[k][s];
        }

        dp[k][s] = 0;

        // 현재 두부를 버리는 경우
        dp[k][s] = Math.max(dp[k][s], dfs(k + 1, s >> 1));

        // 두부를 오른쪽으로 2개 선택
        if ((k % N) != N - 1 && (s & 1) == 0 && (s & 2) == 0) {
            dp[k][s] = Math.max(dp[k][s], dfs(k + 2, s >> 2) + getPrice(k, k + 1));
        }

        // 두부를 아래로 2개 선택
        if ((s & 1) == 0 && k + N < N * N) {
            dp[k][s] = Math.max(dp[k][s], dfs(k + 1, (s >> 1) | (1 << (N - 1))) + getPrice(k, k + N));
        }

        return dp[k][s];
    }

    static int getPrice(int pos1, int pos2) {
        char ch1 = board[pos1 / N][pos1 % N];
        char ch2 = board[pos2 / N][pos2 % N];

        return price[getIndex(ch1)][getIndex(ch2)];
    }

    static int getIndex(char ch) {
        if (ch == 'F') {
            return 3;
        }

        return ch - 'A';
    }
}