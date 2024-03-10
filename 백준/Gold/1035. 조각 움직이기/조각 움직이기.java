import java.io.*;
import java.util.*;

class Main {

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> visited = new HashSet<>();

        int start = 0;

        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                if (input.charAt(j) == '*') {
                    start |= 1 << (5 * i + j);
                }
            }
        }

        visited.add(start);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (check(cur.pos)) {
                System.out.println(cur.count);
                return;
            }

            for (int i = 0; i < 25; i++) {
                if ((cur.pos & (1 << i)) > 0) {
                    int r = i / 5;
                    int c = i % 5;

                    for (int k = 0; k < 4; k++) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];

                        int np = nr * 5 + nc;

                        if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5) {
                            continue;
                        }

                        if ((cur.pos & (1 << np)) > 0) {
                            continue;
                        }

                        int next = (cur.pos | (1 << np)) & ~(1 << i);

                        if (!visited.contains(next)) {
                            queue.offer(new Node(next, cur.count + 1));
                            visited.add(next);
                        }
                    }
                }
            }
        }
    }

    static boolean check(int pos) {
        int count = 0;
        int start = 0;

        for (int i = 0; i < 25; i++) {
            if ((pos & (1 << i)) > 0) {
                start = i;
                count++;
            }
        }

        boolean[] visited = new boolean[25];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        int find = 1;

        while (!queue.isEmpty()) {
            int cur = queue.remove();

            for (int i = 0; i < 4; i++) {
                int r = cur / 5;
                int c = cur % 5;

                int nr = r + dr[i];
                int nc = c + dc[i];

                int np = nr * 5 + nc;

                if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5) {
                    continue;
                }

                if (visited[np]) {
                    continue;
                }

                if ((pos & (1 << np)) > 0) {
                    queue.offer(np);
                    find ++;
                    visited[np] = true;
                }
            }
        }

        return count == find;
    }
}

class Node {
    int pos, count;
    Node(int pos, int count) {
        this.pos = pos;
        this.count = count;
    }
}