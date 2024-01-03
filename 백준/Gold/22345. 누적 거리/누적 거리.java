import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[][] info = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(info, Comparator.comparingInt(a -> a[1]));

        long[][] prefix = new long[N][2];
        long[][] reverse = new long[N][2];

        prefix[0][0] = info[0][0];
        reverse[N - 1][0] = info[N - 1][0];

        for (int i = 1, j = N - 2; i < N; i++, j--) {
            prefix[i][0] = prefix[i - 1][0] + info[i][0];
            prefix[i][1] = prefix[i - 1][1] + prefix[i - 1][0] * (info[i][1] - info[i - 1][1]);

            reverse[j][0] = reverse[j + 1][0] + info[j][0];
            reverse[j][1] = reverse[j + 1][1] + reverse[j + 1][0] * (info[j + 1][1] - info[j][1]);
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine());
            int[] pos = search(info, query);
            long res = 0;

            int left = pos[0];
            int right = pos[1];

            if (right < N) {
                res += reverse[right][1] + reverse[right][0] * (info[right][1] - query);
            }

            if (left > -1) {
                res += prefix[left][1] + prefix[left][0] * (query - info[left][1]);
            }

            answer.append(res).append("\n");
        }

        System.out.print(answer);
    }

    static int[] search(int[][] info, int k) {
        int n = info.length;
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (info[mid][1] < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return new int[]{hi, lo};
    }
}