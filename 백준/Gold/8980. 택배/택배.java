import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int result = 0;

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());

        int[] max = new int[N];

        Arrays.fill(max, C);

        int[][] carry = new int[M][3];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            carry[i][0] = Integer.parseInt(st.nextToken());
            carry[i][1] = Integer.parseInt(st.nextToken());
            carry[i][2] = Integer.parseInt(st.nextToken());
        }

        // 받는 마을 순으로 오름차순 정렬.
        Arrays.sort(carry, (a, b) -> a[1] - b[1]);

        for (int i = 0; i < M; i++) {

            int maxCarry = Integer.MAX_VALUE;

            for (int j = carry[i][0]; j < carry[i][1]; j++) {
                maxCarry = Math.min(maxCarry, max[j]);
            }

            if (maxCarry >= carry[i][2]) {
                for (int j = carry[i][0]; j < carry[i][1]; j++) {
                    max[j] -= carry[i][2];
                }

                result += carry[i][2];
            } else {
                for (int j = carry[i][0]; j < carry[i][1]; j++) {
                    max[j] -= maxCarry;
                }

                result += maxCarry;
            }
        }

        System.out.println(result);
    }
}
