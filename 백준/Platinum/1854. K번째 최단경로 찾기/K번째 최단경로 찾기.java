import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>();
        List<PriorityQueue<Integer>> dist = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            dist.add(new PriorityQueue<>(Comparator.reverseOrder()));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1, 0));
        dist.get(1).offer(0);

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            for (Node next : graph.get(cur.end)) {
                int v = next.end;
                int vw = cur.w + next.w;

                PriorityQueue<Integer> vpq = dist.get(v);

                if (vpq.size() < K) {
                    vpq.offer(vw);
                    queue.offer(new Node(v, vw));
                } else if (vpq.peek() > vw) {
                    vpq.remove();
                    vpq.offer(vw);
                    queue.offer(new Node(v, vw));
                }
            }
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            PriorityQueue<Integer> pq = dist.get(i);
            if (pq.size() < K) {
                answer.append(-1).append("\n");
            } else {
                answer.append(pq.peek()).append("\n");
            }
        }

        System.out.print(answer);
    }
}

class Node implements Comparable<Node> {
    int end;
    int w;

    Node(int end, int w) {
        this.end = end;
        this.w = w;
    }

    @Override
    public int compareTo(Node o) {
        return w - o.w;
    }
}