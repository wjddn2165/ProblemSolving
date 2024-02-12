import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        String input;

        while ((input = br.readLine()) != null) {
            int N = Integer.parseInt(input);
            int[][] coins = new int[N][2];
            int sum = 0;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                coins[i][0] = Integer.parseInt(st.nextToken());
                coins[i][1] = Integer.parseInt(st.nextToken());

                sum += coins[i][0] * coins[i][1];
            }

            if (sum % 2 != 0) {
                answer.append(0).append("\n");
                continue;
            }

            int[] dp = new int[sum + 1];
            dp[0] = 1;

            outer:
            for (int i = 0; i < N; i++) {
                for (int j = 1; j <= coins[i][1]; j++) {
                    int w = coins[i][0] * j;

                    for (int k = sum; k >= w; k--) {
                        if (dp[k] == 1) {
                            continue;
                        }

                        dp[k] = Math.max(dp[k], dp[k - w]);
                        if (dp[sum / 2] == 1) {
                            break outer;
                        }
                    }
                }
            }

            if (dp[sum / 2] == 1) {
                answer.append(1).append("\n");
            } else {
                answer.append(0).append("\n");
            }
        }

        System.out.print(answer);
    }
}