import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int k;
    static int[] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        score = new int[n];

        int lo = 0;
        int hi = 0;

        for (int i = 0; i < n; i++) {
            score[i] = Integer.parseInt(st.nextToken());
            hi += score[i];
        }

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if(decide(mid)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        System.out.println(hi);
    }

    static boolean decide(int m) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += score[i];

            if (sum >= m) {
                count++;
                sum = 0;
            }
        }

        return count >= k;
    }
}