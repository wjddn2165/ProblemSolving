import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Edge>> graph = new ArrayList<>();
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

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        int[][] dp = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            dp[i][0] = INF;
        }

        queue.offer(new Edge(1, 0));

        while (!queue.isEmpty()) {
            Edge cur = queue.remove();
            if (dp[cur.end][0] < cur.weight) {
                continue;
            }

            for (Edge next : graph.get(cur.end)) {
                int curWeight = cur.weight;
                int nextWeight = next.weight;

                if (curWeight + nextWeight < dp[next.end][0]) {
                    dp[next.end][0] = curWeight + nextWeight;
                    dp[next.end][1] = cur.end;
                    queue.offer(new Edge(next.end, dp[next.end][0]));
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append(N - 1).append("\n");

        for (int i = 2; i <= N; i++) {
            answer.append(i).append(" ").append(dp[i][1]).append("\n");
        }

        System.out.print(answer);
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
    public int compareTo(Edge o) {
        return weight - o.weight;
    }
}