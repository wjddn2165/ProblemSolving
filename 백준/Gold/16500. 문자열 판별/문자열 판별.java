import java.io.*;
import java.util.HashSet;
import java.util.Set;

class Main {

    static Set<String> words;
    static boolean[] dp;
    static int N;
    static String S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        N = Integer.parseInt(br.readLine());
        words = new HashSet<>();
        dp = new boolean[S.length()];

        for (int i = 0; i < N; i++) {
            words.add(br.readLine());
        }

        // dp[i] i~끝 까지 만들 수 있는지 여부
        for (int i = S.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < S.length(); j++) {
                if (dp[j]) {
                    if (words.contains(S.substring(i, j))) {
                        dp[i] = true;
                    }
                }
            }

            if (words.contains(S.substring(i))) {
                dp[i] = true;
            }
        }

        System.out.println(dp[0] ? 1 : 0);
    }
}