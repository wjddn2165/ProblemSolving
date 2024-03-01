import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int[] cake;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cake = new int[N];
        dp = new long[N][N];

        for (int i = 0; i < N; i++) {
            cake[i] = Integer.parseInt(br.readLine());
            Arrays.fill(dp[i], -1);
        }

        long answer = 0;

        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, cake[i] + ioi((i - 1 + N) % N, (i + 1) % N));
        }

        System.out.println(answer);
    }

    static long ioi(int left, int right) {
        if (left == right) {
            return 0;
        }

        if (cake[left] > cake[right]) {
            return joi((left - 1 + N) % N, right);
        } else {
            return joi(left, (right + 1) % N);
        }
    }

    static long joi(int left, int right) {
        if (left == right) {
            return cake[left];
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        return dp[left][right] = Math.max(ioi((left - 1 + N) % N, right) + cake[left], ioi(left, (right + 1) % N) + cake[right]);
    }
}