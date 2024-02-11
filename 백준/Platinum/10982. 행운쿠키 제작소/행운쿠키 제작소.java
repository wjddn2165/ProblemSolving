import java.io.*;
import java.util.*;

class Main {

    static int[] dp = new int[100001];
    static final int INF = 987654321;
    static final StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            // dp[i] = 오븐 1이 i만큼 일하지 않는 동안 오븐 2가 최소로 쿠키를 굽는 시간
            Arrays.fill(dp, INF);
            dp[0] = 0;

            int sum = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                sum += a;

                for (int j = 100000; j >= a; j--) {
                    dp[j] = Math.min(dp[j], dp[j - a] + b);
                }
            }

            int result = INF;

            for (int i = 0; i <= sum; i++) {
                result = Math.min(result, Math.max(sum - i, dp[i]));
            }

            answer.append(result).append("\n");
        }

        System.out.print(answer);
    }
}