import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;

        for (int i = 0; i < N; i++) {
            if (arr[i] == 1) {
                count++;
                
                if (i + 1 < N) {
                    arr[i + 1] ^= 1;
                }

                if (i + 2 < N) {
                    arr[i + 2] ^= 1;
                }
            }
        }

        System.out.println(count);
    }
}
