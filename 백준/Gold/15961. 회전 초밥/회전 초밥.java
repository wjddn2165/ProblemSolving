import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int left = 0, right = 0;
        int count = 0;
        int[] eat = new int[d + 1];
        int max = 0;

        while (left < N) {
            // update right
            while (right - left < k) {
                int next = arr[right++ % N];
                if (eat[next]++ == 0) {
                    count++;
                }
            }

            max = Math.max(max, count + (eat[c] == 0 ? 1 : 0));

            // update left
            int remove = arr[left++];
            if(--eat[remove] == 0) {
                count--;
            }
        }

        System.out.println(max);
    }
}