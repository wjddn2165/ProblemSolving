import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<List<Integer>> graph;
    static int[] dp;
    static int[] jump;
    static final int MOD = 998_244_353;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new int[n + 1];
        jump = new int[n + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int root = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            dp[i] = -1;
            int parent = Integer.parseInt(st.nextToken());
            if (parent == 0) {
                root = i;
                continue;
            }
            graph.get(parent).add(i);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            jump[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dfs(root));
    }

    static int dfs(int cur) {
        // 리프노드
        if (graph.get(cur).isEmpty()) {
            return 1;
        }

        if (dp[cur] != -1) {
            return dp[cur];
        }

        dp[cur] = 0;

        if (jump[cur] == 0) {
            return 0;
        }

        List<Integer> children = getChild(cur);
        for (int child : children) {
            dp[cur] += dfs(child);
            dp[cur] %= MOD;
        }

        return dp[cur] % MOD;
    }

    static List<Integer> getChild(int parent) {
        List<Integer> children = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(parent, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (1 <= cur.depth && cur.depth <= jump[parent]) {
                children.add(cur.number);
            }

            if (cur.depth > jump[parent]) {
                break;
            }

            for (int child : graph.get(cur.number)) {
                queue.offer(new Node(child, cur.depth + 1));
            }
        }

        return children;
    }
}

class Node {
    int number;
    int depth;

    public Node(int number, int depth) {
        this.number = number;
        this.depth = depth;
    }
}