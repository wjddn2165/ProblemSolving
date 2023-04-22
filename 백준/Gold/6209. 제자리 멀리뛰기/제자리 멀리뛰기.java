import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int d;
    static int n;
    static int m;
    static int[] pos;

    static int lowerBound() {
        int lo = 1;
        int hi = pos[n+1] - pos[0];

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (isPossible(mid)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return hi;
    }

    static boolean isPossible(int dist) {
        int cur = pos[0];
        int count = 0;

        for (int i = 1; i < n + 1; i++) {
        if (pos[i] - cur >= dist) {
            cur = pos[i];
            count++;

            if (count >= n - m && d - cur >= dist) {
                return true;
            }
        }
    }

        return false;
}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if (n == 0 || n == m) {
            System.out.println(d);
            return;
        }

        pos = new int[n + 2];

        pos[n + 1] = d;

        for (int i = 1; i < n + 1; i++) {
            pos[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(pos);

        System.out.println(lowerBound());
    }
}