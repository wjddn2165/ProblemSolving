import java.io.*;
import java.util.*;

class Main {

    static final int INF = 1987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new Edge(from, to, weight));
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        dist[1] = 0;

        for (int i = 1; i <= N; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] != INF && dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                    if (i == N) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            answer.append(dist[i] == INF ? -1 : dist[i]).append("\n");
        }

        System.out.print(answer);
    }
}

class Edge {
    int from;
    int to;
    int weight;

    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}