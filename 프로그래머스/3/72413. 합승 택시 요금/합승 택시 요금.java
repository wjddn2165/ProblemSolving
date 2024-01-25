import java.util.*;

class Solution {
    
    private List<List<Edge>> graph;
    private final int INF = 10000000;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int weight = fare[2];
            
            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }
        
        int[] minDistA = dijkstra(a, n);
        int[] minDistB = dijkstra(b, n);
        int[] minDistS = dijkstra(s, n);
        
        int answer = INF;
        
        for(int i=1;i<=n;i++) {
            answer = Math.min(answer, minDistS[i] + minDistA[i] + minDistB[i]);
        }
        
        return answer;
    }
    
    private int[] dijkstra(int start, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(start, 0));
        dist[start] = 0;
        
        while(!queue.isEmpty()) {
            Edge cur = queue.remove();
            
            if(cur.getWeight() > dist[cur.getEnd()]) {
                continue;
            }
            
            for(Edge next : graph.get(cur.getEnd())) {
                int sum = cur.getWeight() + next.getWeight();
                if(sum < dist[next.getEnd()]) {
                    dist[next.getEnd()] = sum;
                    queue.offer(new Edge(next.getEnd(), sum));
                }
            }
        }
        
        return dist;
    }
}

class Edge implements Comparable<Edge>{
    private int end;
    private int weight;
    
    Edge(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Edge e) {
        return weight - e.weight;
    }
    
    public int getEnd() {
        return this.end;
    }
    
    public int getWeight() {
        return this.weight;
    }
}