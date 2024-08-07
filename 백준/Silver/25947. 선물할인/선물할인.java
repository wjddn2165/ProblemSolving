import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());

        int[] price = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(price);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (price[i] <= b) {
                pq.offer(price[i]);
                b -= price[i];
                answer++;
            } else {
                pq.offer(price[i]);

                while (!pq.isEmpty() && b < price[i] && a > 0) {
                    a--;
                    int max = pq.remove();
                    b += max >> 1;
                }

                if (b >= price[i]) {
                    b -= price[i];
                    answer++;
                } else {
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}