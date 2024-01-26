import java.io.*;
import java.util.*;

class Main {

    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        parent = new int[N];
        rank = new int[N];
        int[][] line = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            parent[i] = i;
            rank[i] = 1;

            line[i][0] = x1;
            line[i][1] = x2;
            line[i][2] = i;
        }

        Arrays.sort(line, Comparator.comparingInt(a -> a[0]));

        int right = -1;
        int idx = 0;

        for (int i = 0; i < line.length; i++) {
            if (right < line[i][0]) {
                idx = line[i][2];
                right = line[i][1];
            } else {
                union(idx, line[i][2]);
                right = Math.max(right, line[i][1]);
            }
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            if (isConnected(from, to)) {
                answer.append(1).append("\n");
            } else {
                answer.append(0).append("\n");
            }
        }

        System.out.print(answer);
    }

    static int find(int line) {
        if (parent[line] == line) {
            return line;
        }

        return parent[line] = find(parent[line]);
    }

    static void union(int line1, int line2) {
        int root1 = find(line1);
        int root2 = find(line2);

        if (root1 == root2) {
            return;
        }

        if (rank[root1] > rank[root2]) {
            parent[root2] = root1;
        } else if (rank[root1] == rank[root2]) {
            parent[root1] = root2;
            rank[root2]++;
        } else {
            parent[root1] = root2;
        }
    }

    static boolean isConnected(int line1, int line2) {
        return find(line1) == find(line2);
    }
}