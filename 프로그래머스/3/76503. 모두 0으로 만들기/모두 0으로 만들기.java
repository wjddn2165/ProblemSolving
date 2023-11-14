import java.util.*;

class Solution {
    static long[] node;
    static List<List<Integer>> graph;
    static long[] dp;
    static boolean[] visited;
    static long sum;
    public long solution(int[] a, int[][] edges) {
        dp = new long[a.length];
        node = new long[a.length];
        graph = new ArrayList<>();
        visited = new boolean[a.length];
        sum = 0;
        for(int i=0;i<a.length;i++) {
            graph.add(new ArrayList<>());
            node[i] = a[i];
            sum += a[i];
        }
        
        if(sum != 0) return -1;
        
        for(int i=0;i<edges.length;i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        
        dfs(0);
        
        return dp[0];
    }
    
    long dfs(int cur) {
        visited[cur] = true;
        List<Integer> child = graph.get(cur);
        for(int i=0;i<child.size();i++) {
            if(visited[child.get(i)]) continue;
            long childSum = dfs(child.get(i));
            node[cur] += childSum;
            dp[cur] += Math.abs(childSum);
            dp[cur] += dp[child.get(i)];
        }
        
        return node[cur];
    }
}