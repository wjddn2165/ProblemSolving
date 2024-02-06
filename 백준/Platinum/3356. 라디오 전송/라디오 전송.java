import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        String S = br.readLine();

        int[] pi = prefixFunction(S);
        System.out.println(L - pi[L - 1]);
    }

    static int[] prefixFunction(String p) {
        int n = p.length();
        int j = 0;
        int[] pi = new int[n];

        for (int i = 1; i < n; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = pi[j - 1];
            }

            if (p.charAt(i) == p.charAt(j)) {
                pi[i] = ++j;
            }
        }

        return pi;
    }
}