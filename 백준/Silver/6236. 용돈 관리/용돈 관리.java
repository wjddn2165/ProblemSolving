import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] price = new int[N];

        int min = 10001;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(br.readLine());

            min = Math.min(min, price[i]);
            sum += price[i];
        }

        int lo = min;
        int hi = sum;

        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            if(decide(price, mid)) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        System.out.println(lo);
    }

    static boolean decide(int[] price, int k) {
        int count = 1;
        int cur = k;

        for (int i = 0; i < price.length; i++) {
            if(k < price[i]) return false;
            if (cur < price[i]) {
                cur = k;
                count++;

                if(count > M) return false;
            }

            cur -= price[i];
        }

        return count <= M;
    }
}