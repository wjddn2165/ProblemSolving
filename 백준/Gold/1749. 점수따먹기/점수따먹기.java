import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                matrix[i][j] += matrix[i][j-1];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = i; j <= m; j++) {
                int sum = 0;
                for (int k = 1; k <= n; k++) {
                    int next = matrix[k][j] - matrix[k][i-1];
                    if (sum > 0) {
                        sum += next;
                    } else {
                        sum = next;
                    }
                    max = Math.max(max, sum);
                }
            }
        }

        System.out.println(max);
    }
}