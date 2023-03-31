import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        if (N <= K) {
            System.out.println(0);
            return;
        }

        int[] sensor = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            sensor[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sensor);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        int total = 0;

        for (int i = 0; i < N - 1; i++) {
            int dist = sensor[i + 1] - sensor[i];
            pq.offer(dist);
            total += dist;
        }

        for (int i = 0; i < K - 1; i++) {
            int maxDist = pq.remove();

            total -= maxDist;
        }

        System.out.println(total);
    }
}