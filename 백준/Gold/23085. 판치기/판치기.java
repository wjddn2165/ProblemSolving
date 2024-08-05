import java.io.*;
import java.util.*;

class Main {
    static boolean[][] visited;
    static int N;
    static int K;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1][N + 1];

        String input = br.readLine();

        int tail = 0;
        int head = 0;

        for (int i = 0; i < N; i++) {
            if (input.charAt(i) == 'T') {
                tail++;
            } else {
                head++;
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(head, tail, 0));
        visited[head][tail] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (cur.head == 0) {
                System.out.println(cur.count);
                return;
            }

            for (int i = 0; i <= K; i++) {
                int headFlip = i;
                int tailFlip = K - i;

                if (headFlip <= cur.head && tailFlip <= cur.tail) {
                    int nextHead = cur.head - headFlip + tailFlip;
                    int nextTail = cur.tail - tailFlip + headFlip;

                    if (!visited[nextHead][nextTail]) {
                        visited[nextHead][nextTail] = true;
                        queue.offer(new Node(nextHead, nextTail, cur.count + 1));
                    }
                }
            }
        }

        System.out.println(-1);
    }

}

class Node {
    int head;
    int tail;
    int count;

    public Node(int head, int tail, int count) {
        this.head = head;
        this.tail = tail;
        this.count = count;
    }
}
