import java.util.*;

class Solution {
    static long[] node;
    static List<List<Integer>> graph;
    static boolean[] visited;
    static long sum;
    static long answer = 0;
    
    public long solution(int[] a, int[][] edges) {
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
        return answer;
    }
    
    void dfs(int cur) {
        visited[cur] = true;
        List<Integer> child = graph.get(cur);
        for(int i=0;i<child.size();i++) {
            int next = child.get(i);
            if(visited[next]) continue;
            dfs(next);
            node[cur] += node[next];
            answer += Math.abs(node[next]);
        }
    }
}