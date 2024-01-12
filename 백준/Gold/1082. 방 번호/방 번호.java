import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] price = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
            max = Math.max(price[i], max);
        }

        int M = Integer.parseInt(br.readLine());

        String[] dp = new String[51];
        for (int i = 0; i < N; i++) {
            dp[price[i]] = i + "";
        }

        for (int i = 1; i <= M; i++) {
            dp[i] = max(dp[i], dp[i - 1]);
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < N; j++) {
                if (i >= price[j] && dp[i - price[j]] != null) {
                    dp[i] = max(dp[i], dp[i - price[j]] + j);
                }
            }
        }

//        System.out.println(Arrays.deepToString(dp));
        System.out.println(dp[M]);
    }

    static String max(String num1, String num2) {
        if (num1 == null) {
            return num2;
        }

        if (num2 == null) {
            return num1;
        }

        int size1 = num1.length();
        int size2 = num2.length();

        if (size1 > 1 && num1.charAt(0) == '0') {
            return num2;
        }

        if (size2 > 1 && num2.charAt(0) == '0') {
            return num1;
        }

        if (size1 < size2) {
            return num2;
        } else if (size1 > size2) {
            return num1;
        } else {
            for (int i = 0; i < size1; i++) {
                if (num1.charAt(i) < num2.charAt(i)) {
                    return num2;
                } else if (num1.charAt(i) > num2.charAt(i)) {
                    return num1;
                }
            }
        }

        return num1;
    }
}