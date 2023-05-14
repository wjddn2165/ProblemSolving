import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[] visited;
    static boolean[] finished;
    static int[] graph;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            visited = new boolean[n + 1];
            finished = new boolean[n + 1];

            graph = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                graph[i] = Integer.parseInt(st.nextToken());
            }

            count = 0;

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(i);
                }
            }

            sb.append(n - count).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int cur) {
        int next = graph[cur];

        if (!visited[next]) {
            visited[next] = true;
            dfs(next);
        } else if (!finished[next]) {
            count ++;

            for (int i = next; i != cur; i = graph[i]) {
                count++;
            }
        }

        finished[cur] = true;
    }
}