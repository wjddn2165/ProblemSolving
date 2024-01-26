import java.io.*;
import java.util.*;

class Main {

    static String S;
    static String T;
    static List<List<Integer>> pos = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        for (int i = 0; i < 26; i++) {
            pos.add(new ArrayList<>());
        }

        for (int i = 0; i < T.length(); i++) {
            char ch = T.charAt(i);
            pos.get(ch - 'a').add(i);
        }

        int idx = -1;
        int ans = 1;

        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            List<Integer> nextPos = pos.get(ch - 'a');
            if (nextPos.size() == 0) {
                System.out.println(-1);
                return;
            }

            int lowerBound = lowerBound(idx, nextPos);
            if (lowerBound <= idx) {
                ans++;
                idx = nextPos.get(0);
            } else {
                idx = lowerBound;
            }
        }

        System.out.println(ans);
    }

    static int lowerBound(int idx, List<Integer> nextPos) {
        int lo = 0;
        int hi = nextPos.size() - 1;

        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            int midValue = nextPos.get(mid);
            if (midValue > idx) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return nextPos.get(Math.min(lo, nextPos.size() - 1));
    }
}