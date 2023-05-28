import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[100001];
        int[] chase = new int[100001];

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(N, 0));
        chase[N] = -1;
        visited[N] = true;


        while (!queue.isEmpty()) {
            Point cur = queue.remove();

            if (cur.pos == K) {
                sb.append(cur.depth).append("\n");
                LinkedList<Integer> list = new LinkedList<>();
                list.add(K);
                while (chase[K] != -1) {
                    list.addFirst(chase[K]);
                    K = chase[K];
                }
                for (Integer integer : list) {
                    sb.append(integer).append(" ");
                }

                System.out.println(sb);
                return;
            }

            int[] dx = {cur.pos, -1, 1};
            for (int i = 0; i < 3; i++) {
                int nx = cur.pos + dx[i];

                if (nx >= 0 && nx < 100001) {
                    if (!visited[nx]) {
                        chase[nx] = cur.pos;
                        visited[nx] = true;
                        queue.offer(new Point(nx, cur.depth + 1));
                    }
                }
            }
        }

    }
}

class Point {
    int pos;
    int depth;

    public Point(int pos, int depth) {
        this.pos = pos;
        this.depth = depth;
    }
}