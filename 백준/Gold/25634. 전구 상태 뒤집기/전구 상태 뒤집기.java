import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] light = new int[n];
        int[] status = new int[n];
        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            light[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            status[i] = Integer.parseInt(st.nextToken());
            if (status[i] == 1) {
                sum += light[i];
                light[i] *= -1;
            }
        }

        int subSum = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (subSum > 0) {
                subSum += light[i];
            } else {
                subSum = light[i];
            }
            max = Math.max(max, subSum);
        }

        System.out.println(sum + max);
    }
}