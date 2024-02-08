import java.io.*;
import java.util.*;

class Main {
    static int n;
    static int[][] times;
    static int[] dp;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        times = new int[n][2];

        int sumA = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i][0] = Integer.parseInt(st.nextToken());
            times[i][1] = Integer.parseInt(st.nextToken());

            sumA += times[i][0];
        }

        dp = new int[sumA + 1];

        Arrays.fill(dp, INF);
        dp[0] = 0;

        // dp[i] = a가 i만큼 일 하지 않았을 때 b가 일하는 시간의 최솟값
        for (int i = 0; i < n; i++) {
            int a = times[i][0];
            int b = times[i][1];

            for (int j = sumA; j >= times[i][0]; j--) {
                dp[j] = Math.min(dp[j], dp[j - a] + b);
            }
        }

        int answer = INF;

        for (int i = 0; i <= sumA; i++) {
            answer = Math.min(answer, Math.max(dp[i], sumA - i));
        }

        System.out.println(answer);
    }
}