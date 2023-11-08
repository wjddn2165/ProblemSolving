import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] light = new int[n];
        int sum = 0;
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            if (Integer.parseInt(st2.nextToken()) == 1) {
                light[i] = -(Integer.parseInt(st1.nextToken()));
                sum += light[i] * -1;
            } else {
                light[i] = Integer.parseInt(st1.nextToken());
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