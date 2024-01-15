import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[] visited;
    static int[][] graph;

    static int bfs(int A, int B) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(A, 0));
        visited[A] = true;

        while (!queue.isEmpty()) {
            Node current = queue.remove();
            if (current.getNumber() == B) {
                return current.getCount();
            }

            for (int i = 1; i < graph.length; i++) {
                if (graph[current.getNumber()][i] == 1 && !visited[i]) {
                    queue.offer(new Node(i, current.getCount() + 1));
                    visited[i] = true;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        graph = new int[N + 1][N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            graph[parent][child] = graph[child][parent] = 1;
        }

        int result = bfs(A, B);
        System.out.print(result);
    }
}

class Node {
    final private int number;
    final private int count;

    Node(int number, int count) {
        this.number = number;
        this.count = count;
    }

    public int getNumber() {
        return number;
    }

    public int getCount() {
        return count;
    }
}