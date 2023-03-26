import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // arr[i][0] == 데드라인, arr[i][1] == 컵라면 수
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 데드라인 순으로 오름차순 정렬.
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int sum = 0;

        for (int i = 0; i < N; i++) {
            if (arr[i][0] > pq.size()) {
                pq.offer(arr[i][1]);
                sum += arr[i][1];
            } else if (pq.peek() < arr[i][1]) {
                sum -= pq.peek();
                pq.remove();
                pq.offer(arr[i][1]);
                sum += arr[i][1];
            }
        }

        System.out.println(sum);
    }
}