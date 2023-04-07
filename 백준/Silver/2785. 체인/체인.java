import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(st.nextToken()));
        }

        int minChain = pq.remove();
        int count = 0;

        while (N > 1) {
            if (minChain == 1) {
                minChain = pq.remove();
                N -= 2;
            } else {
                minChain--;
                N -= 1;
            }

            count++;
        }

        System.out.println(count);
    }
}