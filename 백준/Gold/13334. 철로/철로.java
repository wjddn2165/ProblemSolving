import java.io.*;
import java.util.Arrays;
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
            if (h > o) {
                arr[i][0] = o;
                arr[i][1] = h;
            } else {
                arr[i][0] = h;
                arr[i][1] = o;

            } 
        }

        int d = Integer.parseInt(br.readLine());

        Arrays.sort(arr, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
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