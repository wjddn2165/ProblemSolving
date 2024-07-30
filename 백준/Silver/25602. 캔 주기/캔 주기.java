import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int K;
    static int[] A;
    static int[][] R;
    static int[][] M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[5];
        R = new int[K][N];
        M = new int[K][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                M[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, A));

    }

    static int dfs(int curDay, int[] A) {
        if (curDay == K) {
            return 0;
        }

        int sum = 0;

        for (int i = 0; i < 5; i++) {
            if (A[i] > 0) {
                A[i] --;

                for (int j = 0; j < 5; j++) {
                    if (A[j] > 0) {
                        A[j] --;

                        sum = Math.max(sum, R[curDay][i] + M[curDay][j] + dfs(curDay + 1, A));
                        A[j]++;
                    }
                }

                A[i]++;
            }
        }

        return sum;
    }
}