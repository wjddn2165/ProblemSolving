import java.io.*;
import java.util.*;

class Main {

    static int N;
    static List<List<Integer>> graph;
    static int[] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        score = new int[N + 1];

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 2; i <= N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            graph.get(parent).add(i);
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = dfs(1);
        System.out.println(Math.max(answer[0], answer[1]));
    }

    static int[] dfs(int cur) {
        int notSelectMaxValue = 0;
        int selectMaxValue = 0;

        List<int[]> children = new ArrayList<>();

        for (int next : graph.get(cur)) {
            children.add(dfs(next));
        }

        for (int[] child : children) {
            notSelectMaxValue += Math.max(child[0], child[1]);
        }

        for (int i = 0; i < children.size(); i++) {
            int[] child = children.get(i);
            selectMaxValue = Math.max(selectMaxValue, notSelectMaxValue - Math.max(child[0], child[1]) + child[0] + score[cur] * score[child[2]]);
        }

        return new int[]{notSelectMaxValue, selectMaxValue, cur};
    }
}