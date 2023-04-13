import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int max = -1;
    static boolean[] visited;
    static int[] nums;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        visited = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dfs(new ArrayList<>());

        System.out.println(max);
    }

    static void dfs(List<Integer> list) {
        if (list.size() == N) {
            int sum = 0;

            for (int i = 0; i < N - 1; i++) {
                sum += Math.abs(list.get(i) - list.get(i + 1));
            }

            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(nums[i]);
                dfs(list);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }
}