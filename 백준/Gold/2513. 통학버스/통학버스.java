import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[100001];
        int min = 100001;
        int max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            arr[x] = count;

            min = Math.min(x, min);
            max = Math.max(x, max);
        }

        int answer = 0;

        int count = 0;
        for (int i = min; i < S; i++) {
            count += arr[i];

            while(count > 0) {
                answer += 2 * (S - i);
                count -= K;
            }
        }

        count = 0;
        for (int i = max; i > S; i--) {
            count += arr[i];

            while (count > 0) {
                answer += 2 * (i - S);
                count -= K;
            }
        }

        System.out.println(answer);
    }
}