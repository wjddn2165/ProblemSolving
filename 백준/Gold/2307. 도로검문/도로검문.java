import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int M;
    static List<List<Edge>> graph;
    static int[] trace;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trace = new int[N + 1];

        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        int minDist = dijkstra();
        List<Integer> minPath = new ArrayList<>();

        for (int i = N; i != 0; i = trace[i]) {
            minPath.add(i);
        }

        int answer = 0;

        for (int i = 0; i < minPath.size() - 1; i++) {
            int from = minPath.get(i + 1);
            int to = minPath.get(i);

            Edge removeEdge = null;
            for (Edge next : graph.get(from)) {
                if (next.end == to) {
                    removeEdge = next;
                    break;
                }
            }

            int weight = removeEdge.weight;
            removeEdge.weight = INF;

            int temp = dijkstra();

            if (temp == INF) {
                System.out.println(-1);
                return;
            }

            answer = Math.max(answer, temp - minDist);
            removeEdge.weight = weight;
        }

        System.out.println(answer);
    }

    static int dijkstra() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        dist[1] = 0;

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(1, 0));

        while (!queue.isEmpty()) {
            Edge cur = queue.remove();

            if (cur.weight > dist[cur.end]) {
                continue;
            }

            for (Edge next : graph.get(cur.end)) {
                if (cur.weight + next.weight < dist[next.end]) {
                    dist[next.end] = cur.weight + next.weight;
                    trace[next.end] = cur.end;
                    queue.offer(new Edge(next.end, cur.weight + next.weight));
                }
            }
        }

        return dist[N];
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