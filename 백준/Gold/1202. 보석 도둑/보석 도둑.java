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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] info = new int[N][2];
        int[] weight = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) {
            weight[i] = Integer.parseInt(br.readLine());
        }

        long result = 0;

        // 보석의 무게순으로 정렬
        Arrays.sort(info, (a, b) -> a[0] - b[0]);

        // 가방의 무게순으로 정렬
        Arrays.sort(weight);

        // 보석의 가치를 내림차순으로 정렬히여 담음
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        int bagIndex = 0;

        for (int i = 0; i < N; i++) {
            if (info[i][0] <= weight[bagIndex]) {
                pq.offer(info[i][1]);
            } else {
                while (info[i][0] > weight[bagIndex]) {
                    bagIndex++;

                    if (!pq.isEmpty()) {
                        result += pq.remove();
                    }

                    // 더 이상 넣을 수 있는 가방이 존재하지 않으면 종료
                    if (bagIndex == K) {
                        System.out.println(result);
                        return;
                    }
                }

                pq.offer(info[i][1]);
            }
        }

        for (int i=bagIndex;i<K && !pq.isEmpty();i++) {
            result += pq.remove();
        }

        System.out.println(result);
    }
}