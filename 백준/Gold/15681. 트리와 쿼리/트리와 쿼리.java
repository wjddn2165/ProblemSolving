import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] dp;
    static boolean[] visited;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        dp = new int[N + 1];
        visited = new boolean[N + 1];

        dfs(R);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine());
            result.append(dp[query]).append("\n");
        }

        System.out.print(result);
    }

    static int dfs(int root) {
        visited[root] = true;

        dp[root] = 1;

        for (int next : graph.get(root)) {
            if (!visited[next]) {
                dp[root] += dfs(next);
            }
        }

        return dp[root];
    }
}