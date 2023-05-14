import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[][] balls = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            balls[i][0] = color;
            balls[i][1] = size;
            balls[i][2] = i;
        }

        Arrays.sort(balls, Comparator.comparingInt(ball -> ball[1]));

        // 결과를 저장하는 배열
        int[] result = new int[N];

        // 각 공의 색깔의 size 합을 저장하는 배열
        int[] colorSum = new int[N + 1];

        // 현재 보고있는 공보다 size 가 작은공을 가리키는 포인터
        int prefix_idx = 0;

        // 포인터까지의 size 누적합
        int sum = 0;

        for (int i = 0; i < N; i++) {
            int color = balls[i][0];
            int size = balls[i][1];
            int index = balls[i][2];

            while (balls[prefix_idx][1] < size) {
                sum += balls[prefix_idx][1];
                colorSum[balls[prefix_idx][0]] += balls[prefix_idx][1];
                prefix_idx++;
            }

            result[index] = sum - colorSum[color];
        }

        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append("\n");
        }

        System.out.print(sb);
    }
}