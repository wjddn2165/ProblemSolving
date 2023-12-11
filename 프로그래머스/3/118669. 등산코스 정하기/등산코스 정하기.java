import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        
        List<List<Path>> graph = new ArrayList<>();
        
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
        }
        
        // 그래프 관계 설정
        for(int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int weight = path[2];
            
            graph.get(from).add(new Path(to, weight));
            graph.get(to).add(new Path(from, weight));
        }
        
        Set<Integer> summitSet = new HashSet<>();
        
        for(int summit : summits) {
            summitSet.add(summit);
        }
        
        PriorityQueue<Node> queue = new PriorityQueue<>();
        
        int[] dist = new int[n + 1];
        
        for(int gate : gates) {
            queue.offer(new Node(gate, 0));
            // dist[gate] = -1;
        }
        
        int minIntensity = Integer.MAX_VALUE;
        int minSummit = Integer.MAX_VALUE;
        
        while(!queue.isEmpty()) {
            Node cur = queue.remove();
            
            if(cur.intensity > minIntensity) {
                break;
            }
            
            if(summitSet.contains(cur.num)) {
                minIntensity = cur.intensity;
                minSummit = Math.min(minSummit, cur.num);
                continue;
            }
                        
            for(Path path : graph.get(cur.num)) {
                int nextIntensity = Math.max(cur.intensity, path.weight);
                
                if(dist[path.num] == 0 || dist[path.num] > nextIntensity) {
                    queue.offer(new Node(path.num, nextIntensity));
                    dist[path.num] = nextIntensity;
                }
            }
        }
        
        return new int[]{minSummit, minIntensity};
    }
}

class Path {
    int num;
    int weight;
    
    Path(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }
}

class Node implements Comparable<Node> {
    int num;
    int intensity;
    
    Node(int num, int intensity) {
        this.num = num;
        this.intensity = intensity;
    }
    
    @Override
    public int compareTo(Node o) {
        return intensity - o.intensity;
    }
}