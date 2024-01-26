import java.io.*;
import java.util.*;

class Main {

    static String S;
    static String T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        Set<Character> set = new HashSet<>();
        for (int i = 0; i < T.length(); i++) {
            set.add(T.charAt(i));
        }

        for (int i = 0; i < S.length(); i++) {
            if (!set.contains(S.charAt(i))) {
                System.out.println(-1);
                return;
            }
        }

        int lo = 0;
        int hi = 1000000;

        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (decide(mid)) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        System.out.println(lo);
    }

    static boolean decide(int n) {
        long patternSize = (long) T.length() * n;
        if (patternSize < S.length()) {
            return false;
        }

        int idx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < T.length(); j++) {
                if (S.charAt(idx) == T.charAt(j)) {
                    idx++;
                    if (idx == S.length()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}