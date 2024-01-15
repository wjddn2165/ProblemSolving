import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String t = br.readLine();
        String p = br.readLine();

        int n = t.length();
        int m = p.length();

        int[] pi = prefixFunction(p);

        List<Integer> ret = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && t.charAt(i) != p.charAt(j)) {
                j = pi[j - 1];
            }

            if (t.charAt(i) == p.charAt(j)) {
                if (j == m - 1) {
                    ret.add(i - j + 1);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append(ret.size()).append("\n");
        for (int idx : ret) {
            answer.append(idx).append(" ");
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