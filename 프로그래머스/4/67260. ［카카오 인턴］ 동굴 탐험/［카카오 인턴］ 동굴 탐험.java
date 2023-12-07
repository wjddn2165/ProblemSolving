import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n];
        for(int i=0;i<n;i++) {
            graph.add(new ArrayList<>());
            inDegree[i] = 1;
        }
        
        inDegree[0] = 0;
        
        for(int[] edge : path) {
            int from = edge[0];
            int to = edge[1];
            
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        
        for(int[] o : order) {
            int from = o[0];
            int to = o[1];
            
            graph.get(from).add(to);
            inDegree[to]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        
        int count = 0;
        
        while(!queue.isEmpty()) {
            int cur = queue.remove();
            count++;
            
            for(int next : graph.get(cur)) {
                if(inDegree[next] == 0) continue;
                inDegree[next]--;
                if(inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        if(count == n) return true;
        else return false;
    }
}