import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph.get(b).add(new Edge(a, s));
            }

            int[] dist = new int[n + 1];
            Arrays.fill(dist, INF);

            PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
            queue.offer(new Edge(c, 0));
            dist[c] = 0;

            while (!queue.isEmpty()) {
                Edge cur = queue.remove();

                if (cur.weight > dist[cur.end]) {
                    continue;
                }

                for (Edge next : graph.get(cur.end)) {
                    if (dist[next.end] > cur.weight + next.weight) {
                        queue.offer(new Edge(next.end, cur.weight + next.weight));
                        dist[next.end] = cur.weight + next.weight;
                    }
                }
            }

            int max = -1;
            int count = 0;

            for (int i = 1; i <= n; i++) {
                if (dist[i] != INF) {
                    count++;
                    max = Math.max(max, dist[i]);
                }
            }

            answer.append(count).append(" ").append(max).append("\n");
        }

        System.out.print(answer);
    }
}

class Edge {
    int end;
    int weight;

    Edge(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}