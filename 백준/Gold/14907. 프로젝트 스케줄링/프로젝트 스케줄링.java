import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] times = new int['Z' - 'A' + 1];
        int[] dp = new int['Z' - 'A' + 1];
        int[] degree = new int[times.length];

        int result = 0;

        Arrays.fill(degree, -1);

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 'A'; i <= 'Z'; i++) {
            graph.add(new ArrayList<>());
        }

        String input;
        while ((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);

            int task = st.nextToken().charAt(0) - 'A';
            int time = Integer.parseInt(st.nextToken());

            result = Math.max(result, time);

            times[task] = time;
            degree[task] = 0;

            if (st.hasMoreTokens()) {
                String pre = st.nextToken();
                for (int i = 0; i < pre.length(); i++) {
                    graph.get(pre.charAt(i) - 'A').add(task);
                    degree[task]++;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.remove();

            for (int next : graph.get(cur)) {
                degree[next]--;

                dp[next] = Math.max(dp[next], times[cur]);

                if (degree[next] == 0) {
                    queue.offer(next);
                    times[next] += dp[next];
                    result = Math.max(result, times[next]);
                }
            }
        }

        System.out.println(result);
    }
}