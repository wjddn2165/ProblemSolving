import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            arr[i][0] = Math.min(h, o);
            arr[i][1] = Math.max(h, o);
        }

        int d = Integer.parseInt(br.readLine());

        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int max = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i][1] - arr[i][0] > d) {
                continue;
            }

            pq.offer(arr[i][0]);
            while (!pq.isEmpty() && pq.peek() < arr[i][1] - d) {
                pq.remove();
            }

            max = Math.max(max, pq.size());
        }

        System.out.println(max);
    }
}