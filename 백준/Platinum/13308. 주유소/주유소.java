import java.io.*;
import java.util.*;

public class Main {

    static final long INF = 100_000_000_000L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[][] dist = new long[N + 1][2501];
        int[] price = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

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
        queue.offer(new Node(1, 0, price[1]));
        dist[1][price[1]] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (cur.w > dist[cur.end][cur.minPrice] || cur.end == N) {
                continue;
            }

            for (Edge next : graph.get(cur.end)) {
                int v = next.end;
                int vw = next.w;

                long nextWeight = cur.w + vw * cur.minPrice;
                int nextMinPrice = Math.min(cur.minPrice, price[v]);
                if (dist[v][nextMinPrice] > nextWeight) {
                    dist[v][nextMinPrice] = nextWeight;
                    queue.offer(new Node(v, nextWeight, nextMinPrice));
                }
            }
        }

        long answer = INF;
        for (int i = 0; i <= 2500; i++) {
            answer = Math.min(answer, dist[N][i]);
        }

        System.out.println(answer);
    }
}

class Node implements Comparable<Node> {
    int end;
    long w;
    int minPrice;


    Node(int end, long w, int minPrice) {
        this.end = end;
        this.w = w;
        this.minPrice = minPrice;
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