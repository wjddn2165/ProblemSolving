import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        int[] parents = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n; i++) {
            graph.get(parents[i]).add(i);
        }

        System.out.println(dfs(graph, 0) - 1);
    }

    static int dfs(List<List<Integer>> graph, int cur) {
        if (graph.get(cur).isEmpty()) {
            return 1;
        }

        List<Integer> list = new ArrayList<>();

        for (int next : graph.get(cur)) {
            list.add(dfs(graph, next));
        }

        list.sort(Comparator.reverseOrder());

        int max = 0;
        int count = 0;

        for (int i = 0; i < list.size(); i++) {
            max = Math.max(max, list.get(i) + count);
            count++;
        }

        return max + 1;
    }
}