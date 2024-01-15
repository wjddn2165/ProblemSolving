import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int[] pi = prefixFunction(str);
        System.out.println(N - pi[N - 1]);
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