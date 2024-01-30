import java.io.*;
import java.util.*;

public class Main {

    static final long INF = 1_000_000_000_000L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[][] dist = new long[N + 1][K + 1];

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1, 0, 0));
        dist[1][0] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (cur.w > dist[cur.end][cur.k]) {
                continue;
            }

            if (cur.end == N) {
                continue;
            }

            for (Edge next : graph.get(cur.end)) {
                int v = next.end;
                int vw = next.w;

                // 현재 도로 포장하는 경우
                if (cur.k < K && dist[v][cur.k + 1] > cur.w) {
                    dist[v][cur.k + 1] = cur.w;
                    queue.offer(new Node(v, cur.w, cur.k + 1));
                }

                // 현재 도로 포장하지 않는 경우
                if (dist[v][cur.k] > cur.w + vw) {
                    dist[v][cur.k] = cur.w + vw;
                    queue.offer(new Node(v, cur.w + vw, cur.k));
                }
            }
        }

        long answer = INF;
        for (int i = 0; i <= K; i++) {
            answer = Math.min(answer, dist[N][i]);
        }

        System.out.println(answer);
    }
}

class Node implements Comparable<Node> {
    int end;
    long w;
    int k;


    Node(int end, long w, int k) {
        this.end = end;
        this.w = w;
        this.k = k;
    }

    @Override
    public int compareTo(Node o) {
        return (int) (w - o.w);
    }
}

class Edge {
    int end;
    int w;

    Edge(int end, int w) {
        this.end = end;
        this.w = w;
    }
}