import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<List<Integer>> graph;
    static int[] height;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        height = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1];
        int[] degree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (height[from] < height[to]) {
                graph.get(from).add(to);
                degree[to]++;
            } else {
                graph.get(to).add(from);
                degree[from]++;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                dfs(i);
            }
        }

        for (int i = 1; i <= N; i++) {
            result.append(dp[i]).append("\n");
        }

        System.out.print(result);
    }

    static int dfs(int cur) {
        if (dp[cur] != 0) {
            return dp[cur];
        }

        dp[cur] = 1;

        for (int next : graph.get(cur)) {
            if (height[cur] < height[next]) {
                dp[cur] = Math.max(dp[cur], dfs(next) + 1);
            }
        }

        return dp[cur];
    }
}