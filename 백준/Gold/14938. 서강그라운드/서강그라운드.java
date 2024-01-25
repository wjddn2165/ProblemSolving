import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int M;
    static int R;
    static int[] items;
    static List<List<Edge>> graph;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        items = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, dijkstra(i));
        }

        System.out.println(answer);
    }

    static int dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        dist[start] = 0;

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(start, 0));

        while (!queue.isEmpty()) {
            Edge cur = queue.remove();

            if (cur.weight > dist[cur.end]) {
                continue;
            }

            for (Edge next : graph.get(cur.end)) {
                int sum = cur.weight + next.weight;
                if (sum < dist[next.end]) {
                    dist[next.end] = sum;
                    queue.offer(new Edge(next.end, sum));
                }
            }
        }

        int total = 0;

        for (int i = 1; i <= N; i++) {
            if (dist[i] <= M) {
                total += items[i];
            }
        }

        return total;
    }
}

class Edge implements Comparable<Edge> {
    int end;
    int weight;

    Edge(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return weight - e.weight;
    }
}