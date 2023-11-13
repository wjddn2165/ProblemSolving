import java.io.*;
import java.util.*;

class Main {
    static final int SIZE = 3;
    static int[] dx = {-SIZE, 1, SIZE, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int zeroIdx = 0;
        for (int i = 0; i < SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                String next = st.nextToken();
                if (next.equals("0")) {
                    zeroIdx = i * SIZE + j;
                    sb.append(9);
                    continue;
                }
                sb.append(next);
            }
        }
        int src = Integer.parseInt(sb.toString());
        int target = 123456789;
        Set<Integer> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(src, zeroIdx, 0));
        visited.add(src);

        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            if (cur.state == target) {
                System.out.println(cur.count);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.zeroIdx + dx[i];
                if (nx < 0 || nx >= SIZE * SIZE) continue;
                if ((cur.zeroIdx == 2 || cur.zeroIdx == 5) && i == 1) continue;
                if ((cur.zeroIdx == 3 || cur.zeroIdx == 6) && i == 3) continue;
                int next = changePos(cur.state, cur.zeroIdx, nx);
                if (!visited.contains(next)) {
                    queue.offer(new Node(next, nx, cur.count + 1));
                    visited.add(next);
                }
            }
        }
        System.out.println(-1);
    }
    static int changePos(int state, int zeroIdx, int nx) {
        StringBuilder sb = new StringBuilder(String.valueOf(state));
        char a = sb.charAt(zeroIdx);
        char b = sb.charAt(nx);
        sb.setCharAt(zeroIdx, b);
        sb.setCharAt(nx, a);
        return Integer.parseInt(sb.toString());
    }
}

class Node {
    int state;
    int zeroIdx;
    int count;

    Node(int state, int zeroIdx, int count) {
        this.state = state;
        this.zeroIdx = zeroIdx;
        this.count = count;
    }
}