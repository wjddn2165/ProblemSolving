import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<List<Integer>> graph;
    static int[][] dp;
    static int[] population;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        population = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        visited = new boolean[n + 1];

        dp = new int[n + 1][2];

        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dfs(int cur) {
        visited[cur] = true;

        dp[cur][0] = 0;
        dp[cur][1] = population[cur];

        for (int next : graph.get(cur)) {
            if (!visited[next]) {
                dfs(next);

                dp[cur][0] += Math.max(dp[next][0], dp[next][1]);
                dp[cur][1] += dp[next][0];
            }
        }
    }
}