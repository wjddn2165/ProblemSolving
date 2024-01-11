import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] dp;
    static int[][] quest;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[1001][1001];
        visited = new boolean[N];
        quest = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            quest[i][0] = Integer.parseInt(st.nextToken());
            quest[i][1] = Integer.parseInt(st.nextToken());
            quest[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= 1000; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(1, 1));
    }

    static int dfs(int str, int intel) {
        if (dp[str][intel] != -1) {
            return dp[str][intel];
        }

        dp[str][intel] = 0;
        List<Integer> finished = new ArrayList<>();
        int point = 0;
        int count = 0;

        for (int i = 0; i < N; i++) {
            if (quest[i][0] > str && quest[i][1] > intel) {
                continue;
            }

            count++;

            if (visited[i]) {
                continue;
            }

            point += quest[i][2];
            visited[i] = true;
            finished.add(i);
        }

        for (int i = 0; i <= point; i++) {
            count = Math.max(count, dfs(Math.min(1000, str + i), Math.min(1000, intel + point - i)));
        }

        for (int i = 0; i < finished.size(); i++) {
            visited[finished.get(i)] = false;
        }

        return dp[str][intel] = count;
    }
}