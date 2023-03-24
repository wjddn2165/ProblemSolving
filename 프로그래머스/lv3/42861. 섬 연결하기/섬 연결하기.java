import java.util.*;
class Solution {
    
    int[] parents;
    
    int find(int node) {
        if(parents[node] == node) {
            return node;
        }
        
        return parents[node] = find(parents[node]);
    }
    
    boolean union(int nodeA, int nodeB) {
        int rootA = find(nodeA);
        int rootB = find(nodeB);
        
        if(rootA == rootB) return false;
        
        parents[rootA] = parents[rootB];
        return true;
    }
    
    public int solution(int n, int[][] costs) {
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        
        for(int[] cost : costs) {
            edges.offer(new Edge(cost[0], cost[1], cost[2]));
        }
        
        parents = new int[n];
        
        for(int i=0;i<n;i++) {
            parents[i] = i;
        }
        
        int answer = 0;
        int count = 0;
        
        while(!edges.isEmpty()) {
            Edge edge = edges.remove();
            
            if(union(edge.from, edge.to)) {
                answer += edge.weight;
                count++;
                
                if(count == n - 1) break;
            }
        }
        return answer;
    }
}

class Edge implements Comparable<Edge> {
    int from;
    int to;
    int weight;
    
    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Edge o) {
        return weight - o.weight;
    }
}