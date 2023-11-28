import java.util.*;

class Solution {
    public int solution(int[] sales, int[][] links) {
        int n = sales.length;
        
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] link : links) {
            int from = link[0];
            int to = link[1];
            
            graph.get(from).add(to);
        }
        
        int[] result = dfs(1, graph, sales);
        
        return Math.min(result[0], result[1]);
    }
    
    int[] dfs(int cur, List<List<Integer>> graph, int[] sales) {
        if(graph.get(cur).isEmpty()) {
            return new int[]{0, sales[cur - 1]};
        }
        
        List<int[]> children = new ArrayList<>();
        
        for(int next : graph.get(cur)) {
            children.add(dfs(next, graph, sales));
        }
        
        int minSum = 0;
        for(int i=0;i<children.size();i++) {
            int[] child = children.get(i);
            minSum += Math.min(child[0], child[1]);
        }
        
        int min = Integer.MAX_VALUE;
        
        for(int i=0;i<children.size();i++) {
            int[] child = children.get(i);
            int sum = minSum + child[1] - Math.min(child[0], child[1]);
            min = Math.min(min, sum);
        }
        
        return new int[]{min, minSum + sales[cur - 1]};
    }
}