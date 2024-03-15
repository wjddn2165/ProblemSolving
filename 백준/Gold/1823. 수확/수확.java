import java.io.*;
import java.util.*;

class Main {

    static int[] score;
    static int[][] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        score = new int[N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(br.readLine());
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, N - 1));
    }

    static int dfs(int left, int right) {
        int k = N - (right - left);
        if (left == right) {
            return score[left] * k;
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        dp[left][right] = Math.max(dfs(left + 1, right) + score[left] * k, dfs(left, right - 1) + score[right] * k);
        
        return dp[left][right];
    }
}