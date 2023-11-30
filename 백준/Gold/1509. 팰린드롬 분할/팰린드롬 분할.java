import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = str.length();
        int[] dp = new int[n + 1];

        boolean[][] isPalindrome = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            outer:for (int j = i; j < n; j++) {
                int size = (j - i + 1) >> 1;
                for (int k = 0; k < size; k++) {
                    if (str.charAt(i + k) != str.charAt(j - k)) {
                        continue outer;
                    }
                }

                isPalindrome[i][j] = true;
            }
        }

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = i - 1; j >= 1; j--) {
                if (isPalindrome[j - 1][i - 1]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }

        System.out.println(dp[n]);
    }
}