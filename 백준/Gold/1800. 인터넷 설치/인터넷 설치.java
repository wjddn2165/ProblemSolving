import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 987654321;
    static int N;
    static int P;
    static int K;

    static List<List<Node>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        long lo = 0;
        long hi = 100_000_000_000L;

        long answer = -1;

        while (lo <= hi) {
            long mid = (lo + hi) >> 1;
            if (dijkstra(mid)) {
                hi = mid - 1;
                answer = mid;
            } else {
                lo = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean dijkstra(long k) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        queue.offer(new Node(1, 0));
        dist[1] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (cur.w > dist[cur.end]) {
                continue;
            }

            if (cur.end == N) {
                continue;
            }

            for (Node next : graph.get(cur.end)) {
                int v = next.end;
                int vw = next.w;

                int nextWeight = cur.w + (vw > k ? 1 : 0);

                if (dist[v] > nextWeight) {
                    dist[v] = nextWeight;
                    queue.offer(new Node(v, nextWeight));
                }
            }
        }

        return dist[N] <= K;
    }
}

class Node implements Comparable<Node>{
    int end, w;

    Node(int end, int w) {
        this.end = end;
        this.w = w;
    }

    @Override
    public int compareTo(Node o) {
        return w - o.w;
    }
}