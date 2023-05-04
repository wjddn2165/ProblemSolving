import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (N == 0 && A == 0 && B == 0) {
                break;
            }

            // A와 B 거리의 차가 가장 큰놈들로 우선 정렬
            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Math.abs(b[1] - b[2]) - Math.abs(a[1] - a[2]));

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int K = Integer.parseInt(st.nextToken());
                int distA = Integer.parseInt(st.nextToken());
                int distB = Integer.parseInt(st.nextToken());

                queue.offer(new int[]{K, distA, distB});
            }

            int result = 0;

            while (!queue.isEmpty()) {
                int[] next = queue.remove();

                // A가 더 가까운 경우
                if (next[1] <= next[2]) {
                    if (A >= next[0]) {
                        A -= next[0];
                        result += next[1] * next[0];
                    } else {
                        result += next[1] * A;
                        result += (next[0] - A) * next[2];
                        B -= (next[0] - A);
                        A = 0;
                    }
                }

                // B가 더 가까운 경우
                if (next[1] > next[2]) {
                    if (B >= next[0]) {
                        B -= next[0];
                        result += next[2] * next[0];
                    } else {
                        result += next[2] * B;
                        result += (next[0] - B) * next[1];
                        A -= (next[0] - B);
                        B = 0;
                    }
                }
            }

            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }
}