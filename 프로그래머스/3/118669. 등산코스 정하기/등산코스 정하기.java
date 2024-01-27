import java.util.*;

class Solution {
    
    final int INF = 987654321;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Set<Integer> gateSet = new HashSet<>();
        Set<Integer> summitSet = new HashSet<>();
        
        List<List<Node>> graph = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int weight = path[2];
            
            graph.get(from).add(new Node(to, weight));
            graph.get(to).add(new Node(from, weight));
        }
        
        for(int i=0;i<gates.length;i++) {
            gateSet.add(gates[i]);
        }
        
        for(int i=0;i<summits.length;i++) {
            summitSet.add(summits[i]);
        }
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        
        for(int gate : gateSet) {
            queue.offer(new Node(gate, 0));
            dist[gate] = 0;
        }
        
        while(!queue.isEmpty()) {
            Node cur = queue.remove();
            
            if(cur.weight > dist[cur.num]) {
                continue;
            }
            
            if(summitSet.contains(cur.num)) {
                continue;
            }
            
            for(Node next : graph.get(cur.num)) {
                if(gateSet.contains(next.num)) continue;
                int nextWeight = Math.max(cur.weight, next.weight);
                if(dist[next.num] > nextWeight) {
                    dist[next.num] = nextWeight;
                    queue.offer(new Node(next.num, nextWeight));
                }
            }
        }
        
        int[] answer = {-1, INF};
        
        Arrays.sort(summits);
        
        for(int summit : summits) {
            if(answer[1] > dist[summit]) {
                answer[0] = summit;
                answer[1] = dist[summit];
            }
        }
        
        return answer;
    }
}

class Node implements Comparable<Node>{
    int num, weight;
    Node(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}