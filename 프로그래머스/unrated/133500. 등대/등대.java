import java.util.*;

class Solution {
    
    List<List<Integer>> graph;
    boolean[] visited;
    int count = 0;
    
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        
        graph = new ArrayList<>();
        
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] edge : lighthouse) {
            int from = edge[0];
            int to = edge[1];
            
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        
        visited = new boolean[n + 1];
        
        dfs(1);
        
        return count;
    }
    
    boolean dfs(int cur) {
        visited[cur] = true;
        
        boolean flag = true;
        boolean isLeaf = true;
        
        for(int next : graph.get(cur)) {
            if(!visited[next]) {
                flag = dfs(next) && flag;
                isLeaf = false;
            }
        }
        
        if(isLeaf) {
            return false;
        }
        
        if(flag) {
            return false;
        }
        
        count++;
        
        return true;
    }
}