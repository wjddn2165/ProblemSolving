import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int test = 0; test < t; test++) {
            int n = Integer.parseInt(br.readLine());

            int[] stock = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                stock[i] = Integer.parseInt(st.nextToken());
            }

            int max = stock[n - 1];

            long sum = 0;

            for (int i = n - 2; i >= 0; i--) {
                if (stock[i] < max) {
                    sum += max - stock[i];
                } else if (stock[i] > max) {
                    max = stock[i];
                }
            }

            result.append(sum).append("\n");
        }

        System.out.print(result);
    }
}