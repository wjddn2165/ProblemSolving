import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int K;
    static List<List<Edge>> graph;
    static int[][] dp;
    static int[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cards = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = st.nextToken().charAt(0);
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N][M + 1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        graph = new ArrayList<>();

        for (int i = 0; i <= M; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int color = st.nextToken().charAt(0);

            graph.get(from).add(new Edge(to, color));
            graph.get(to).add(new Edge(from, color));
        }

        System.out.println(dfs(0, 1));
    }

    static int dfs(int idx, int num) {
        if (idx == N) {
            return 0;
        }

        if (dp[idx][num] != -1) {
            return dp[idx][num];
        }

        dp[idx][num] = 0;

        for (Edge next : graph.get(num)) {
            if (cards[idx] == next.color) {
                dp[idx][num] = Math.max(dp[idx][num], dfs(idx + 1, next.num) + 10);
            } else {
                dp[idx][num] = Math.max(dp[idx][num], dfs(idx + 1, next.num));
            }
        }

        return dp[idx][num];
    }
}

class Edge {
    int num;
    int color;

    Edge(int num, int color) {
        this.num = num;
        this.color = color;
    }
}