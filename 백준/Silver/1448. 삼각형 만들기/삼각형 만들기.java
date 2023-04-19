import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        while (pq.size() >= 3) {
            int a = pq.remove();
            int b = pq.remove();
            int c = pq.remove();

            if (a < b + c) {
                System.out.println(a + b + c);
                return;
            } else {
                pq.offer(b);
                pq.offer(c);
            }
        }

        System.out.println(-1);
    }
}