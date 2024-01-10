import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, Integer> outdegree = new HashMap<>();

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>());

            graph.get(edge[0]).add(edge[1]);

            indegree.put(edge[1], indegree.getOrDefault(edge[1], 0) + 1);
            outdegree.put(edge[0], outdegree.getOrDefault(edge[0], 0) + 1);
        }

        int center = 0;

        for (int num : outdegree.keySet()) {
            if (indegree.get(num) == null && outdegree.get(num) >= 2) {
                center = num;
                break;
            }
        }

        int[] answer = new int[4];
        answer[0] = center;

        boolean[] visited = new boolean[1000001];

        for (int next : graph.get(center)) {
            List<Integer> result = new ArrayList<>();
            bfs(next, graph, visited, result);

            int nodeCount = result.size();
            int edgeCount = 0;
            for (int res : result) {
                edgeCount += outdegree.getOrDefault(res, 0);
            }

            if (nodeCount == edgeCount) {
                answer[1]++;
            } else if (nodeCount == edgeCount + 1) {
                answer[2]++;
            } else {
                answer[3]++;
            }
        }

        return answer;
    }

    void bfs(int start, Map<Integer, List<Integer>> graph, boolean[] visited, List<Integer> result) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;


        while (!queue.isEmpty()) {
            int cur = queue.remove();
            result.add(cur);
            if (graph.get(cur) == null) {
                continue;
            }

            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    queue.offer(next);
                }
                visited[next] = true;
            }
        }
    }
}
