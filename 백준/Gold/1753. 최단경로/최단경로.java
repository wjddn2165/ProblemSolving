import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[] dist = new int[V + 1];
        Arrays.fill(dist, INF);

        int src = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        dist[src] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.remove();
            int w = cur.getWeight();
            int u = cur.getNumber();

            if (dist[u] < w) {
                continue;
            }

            for (Node next : graph.get(u)) {
                int v = next.getNumber();
                int vw = w + next.getWeight();
                if (dist[v] > vw) {
                    dist[v] = vw;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) {
                sb.append("INF");
            } else
                sb.append(dist[i]);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}

class Node implements Comparable<Node> {
    private final int number;
    private final int weight;

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }

    public int getNumber() {
        return number;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.getWeight();
    }
}