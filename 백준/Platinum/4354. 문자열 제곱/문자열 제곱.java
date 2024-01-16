import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        String str;
        while (!(str = br.readLine()).equals(".")) {
            int[] pi = prefixFunction(str);
            int n = str.length();
            int pattern = n - pi[n - 1];

            if ((n % pattern) == 0) {
                answer.append(n / pattern).append("\n");
            } else {
                answer.append(1).append("\n");
            }
        }

        System.out.print(answer);
    }

    static int[] prefixFunction(String str) {
        int n = str.length();
        int[] pi = new int[n];

        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = pi[j - 1];
            }

            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }

            pi[i] = j;
        }

        return pi;
    }
}