import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static List<List<Edge>> graph;
    static int[] dp;
    static int[] trace;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, score));
        }

        dp = new int[N + 1];
        trace = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = -1;
        }

        StringBuilder answer = new StringBuilder();
        answer.append(dfs(1, true)).append("\n");

        answer.append(1).append(" ");

        for (int i = trace[1]; ; i = trace[i]) {
            answer.append(i).append(" ");
            if (i == 1) {
                break;
            }
        }

        System.out.print(answer);
    }

    static int dfs(int cur, boolean isStart) {
        if (!isStart && cur == 1) {
            return 0;
        }

        if (dp[cur] != -1) {
            return dp[cur];
        }

        dp[cur] = 0;

        for (Edge next : graph.get(cur)) {
            int nextScore = dfs(next.end, false) + next.score;
            if (dp[cur] < nextScore) {
                dp[cur] = nextScore;
                trace[cur] = next.end;
            }
        }

        return dp[cur];
    }
}

class Edge {
    int end;
    int score;

    Edge(int end, int score) {
        this.end = end;
        this.score = score;
    }
}