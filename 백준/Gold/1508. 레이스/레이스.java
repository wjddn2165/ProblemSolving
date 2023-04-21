import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int N;
    static int M;
    static int K;
    static char[] result;

    static void lowerBound() {
        int lo = 1;
        int hi = arr[K - 1] - arr[0];

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (isPossible(mid)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        findIndex(hi);
    }

    static boolean isPossible(int dist) {
        int cur = arr[0];
        int count = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - cur >= dist) {
                cur = arr[i];
                count++;

                if (count >= M) {
                    return true;
                }
            }
        }

        return false;
    }

    static void findIndex(int maxDist) {
        int cur = arr[0];
        int count = 1;

        result = new char[K];
        Arrays.fill(result, '0');
        result[0] = '1';

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - cur >= maxDist) {
                cur = arr[i];
                count++;
                result[i] = '1';

                if (count >= M) {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[K];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lowerBound();

        System.out.println(result);
    }
}