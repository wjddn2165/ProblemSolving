import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] dx = {0, a, b};

        boolean[] visited = new boolean[N + 1];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(N, 0));
        visited[N] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (cur.pos == 0) {
                System.out.println(cur.time);
                return;
            }

            int curPos = cur.pos - 1;

            for (int i = 0; i < dx.length; i++) {
                int nextPos = curPos - dx[i];

                if (nextPos >= 0 && !visited[nextPos]) {
                    visited[nextPos] = true;
                    queue.offer(new Node(nextPos, cur.time + 1));
                }
            }
        }
    }

}

class Node {
    int pos;
    int time;

    public Node(int pos, int time) {
        this.pos = pos;
        this.time = time;
    }
}
