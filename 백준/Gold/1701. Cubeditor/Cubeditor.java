import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = str.length();

        int max = 0;

        for (int i = 0; i < n - 1; i++) {
            max = Math.max(max, prefixFunction(str.substring(i)));
        }

        System.out.println(max);
    }

    static int prefixFunction(String str) {
        int n = str.length();
        int[] pi = new int[n];

        int max = 0;

        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = pi[j - 1];
            }

            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }

            pi[i] = j;
            max = Math.max(max, pi[i]);
        }

        return max;
    }
}