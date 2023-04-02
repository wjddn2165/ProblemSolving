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

        int[][] info = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        // 주유소의 정보를 가까운 순으로 정렬
        Arrays.sort(info, (a, b) -> a[0] - b[0]);

        // 지나온 거리에서 지나친 주유소들의 기름 양을 내림차순으로 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        int count = 0;

        for (int i = 0; i < N; i++) {
            // 현재 가진 기름으로 해당 주유소까지 갈 수 있다면 우선순위 큐에 삽입
            if (P >= info[i][0]) {
                pq.offer(info[i][1]);
            } else {
                do {
                    // 지나온 모든 주유소에 있는 기름을 충전해도 해당 주유소에 도달하지 못하는 경우 -1 출력
                    if (pq.isEmpty()) {
                        System.out.println(-1);
                        return;
                    }

                    P += pq.remove();
                    count++;

                } while (P < info[i][0]);

                pq.offer(info[i][1]);
            }
        }

        // 마지막 주유소까지 왔을 때, 도착지까지 최소 몇번으로 도달할 수 있는지 계산
        while (L > P) {
            if (pq.isEmpty()) {
                System.out.println(-1);
                return;
            }

            P += pq.remove();
            count++;
        }

        System.out.println(count);
    }
}