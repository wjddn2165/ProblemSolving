import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int[] times;

    static long upperBound() {
        long lo = 1;
        long hi = N * 30L;

        while (lo <= hi) {
            long mid = (lo + hi) / 2;
            if (decide(mid)) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    static boolean decide(long k) {
        long sum = 0;

        for (int i = 0; i < M; i++) {
            sum += k / times[i] + 1;

            if (sum >= N) {
                return true;
            }
        }

        return false;
    }

    static int findIndex(long k) {
        long sum = 0;

        for (int i = 0; i < M; i++) {
            sum += k / times[i] + 1;
        }

        int index = -1;

        for (int i = M - 1; i >= 0; i--) {
            if (k % times[i] == 0) {
                sum--;

                if (sum == N - 1) {
                    index = i;
                }
            }
        }

        return index + 1;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (N <= M) {
            System.out.println(N);
            return;
        }

        times = new int[M];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(findIndex(upperBound()));
    }
}