import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] pos = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pos);
        int count = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int idx = lowerBound(pos, M, x);

            if (y <= L - Math.abs(pos[idx] - x)) {
                count++;
            }
        }

        System.out.println(count);
    }

    static int lowerBound(int[] pos ,int M, int k) {
        int l = 0, r = M - 1;

        while (l <= r) {
            int mid = (l + r) >> 1;
            if (pos[mid] > k) {
                r = mid - 1;
            } else if (pos[mid] == k) {
                return mid;
            } else {
                l = mid + 1;
            }
        }

        if(l == 0) return 0;
        if(l == M) return M - 1;
        return k - pos[l - 1] < pos[l] - k ? l - 1 : l;
    }
}