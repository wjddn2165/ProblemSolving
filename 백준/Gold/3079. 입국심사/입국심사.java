import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] times = new long[N];
        long maxTime = 0;

        for (int i = 0; i < N; i++) {
            times[i] = Long.parseLong(br.readLine());
            maxTime = Math.max(maxTime, times[i]);
        }

        long lo = 1;
        long hi = maxTime * M;

        while (lo <= hi) {
            long mid = (lo + hi) / 2;
            if(decide(times, M, mid)) hi = mid - 1;
            else lo = mid + 1;
        }
        System.out.println(lo);
    }

    static boolean decide(long[] times, int M, long key) {
        long sum = 0;
        for (long time : times) {
            sum += key / time;

            if (sum >= M) {
                return true;
            }
        }

        return false;
    }
}