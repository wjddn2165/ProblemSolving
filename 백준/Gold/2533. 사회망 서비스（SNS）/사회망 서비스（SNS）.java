import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<List<Integer>> graph;
    static boolean[] visited;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        visited = new boolean[n + 1];

        dfs(1);

        System.out.println(count);
    }

    static boolean dfs(int cur) {
        visited[cur] = true;

        boolean flag = true;
        boolean isLeaf = true;

        for(int next : graph.get(cur)) {
            if (!visited[next]) {
                isLeaf = false;
                flag = dfs(next) && flag;
            }
        }

        if (isLeaf) {
            return false;
        }

        if (flag) {
            return false;
        }

        count++;
        return true;
    }
}