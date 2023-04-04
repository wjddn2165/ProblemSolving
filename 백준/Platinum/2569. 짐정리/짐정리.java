import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static void bfs(int number, Map<Integer, Integer> graph, Set<Integer> visited, List<List<Integer>> cycles) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(number);
        visited.add(number);

        List<Integer> cycle = new ArrayList<>();
        cycle.add(number);

        while (!queue.isEmpty()) {
            int cur = queue.remove();
            int next = graph.get(cur);

            if (!visited.contains(next)) {
                queue.offer(next);
                visited.add(next);
                cycle.add(next);
            }
        }
        cycles.add(cycle);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int[] sortedNums = new int[N];

        int minWeight = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            sortedNums[i] = nums[i];
            if (minWeight > nums[i]) minWeight = nums[i];
        }

        Arrays.sort(sortedNums);

        int answer = 0;

        Map<Integer, Integer> graph = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        List<List<Integer>> cycles = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            graph.put(sortedNums[i], nums[i]);
        }

        for (Integer number : graph.keySet()) {
            if (!visited.contains(number)) {
                bfs(number, graph, visited, cycles);
            }
        }

        for (List<Integer> cycle : cycles) {
            if(cycle.size() == 1) continue;

            int curCycleSum = 0;
            int minCycleWeight = Integer.MAX_VALUE;

            for (int weight : cycle) {
                curCycleSum += weight;
                if(weight < minCycleWeight) minCycleWeight = weight;
            }

            if (cycle.size() == 2) {
                answer += curCycleSum;
                continue;
            }

            int changeCycleSum = curCycleSum;
            curCycleSum += (cycle.size() - 2) * minCycleWeight;

            if (minWeight == minCycleWeight) {
                answer += curCycleSum;
                continue;
            }

            changeCycleSum -= minCycleWeight;
            changeCycleSum += (cycle.size() - 1) * minWeight;
            changeCycleSum += (minCycleWeight + minWeight) * 2;

            answer += Math.min(changeCycleSum, curCycleSum);
        }

        System.out.println(answer);
    }
}