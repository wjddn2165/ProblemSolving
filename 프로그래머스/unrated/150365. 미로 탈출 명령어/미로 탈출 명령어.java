import java.util.*;

class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        
        String[] dir = {"u", "r", "d", "l"};
                
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(x, y, 0, ""));
        
        while(!queue.isEmpty()) {
            Node cur = queue.remove();
            
            int minDist = Math.abs(cur.r - r) + Math.abs(cur.c - c);
            int remainDist = k - cur.dist;
            
            if(remainDist < minDist || (remainDist - minDist) % 2 != 0) {
                continue;
            }
            
            if(cur.dist == k && r == cur.r && c == cur.c) {
                return cur.path;
            }
            
            for(int i=0;i<4;i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                
                if(cur.dist < k && nr >= 1 && nr <= n && nc >= 1 && nc <= m) {
                    queue.offer(new Node(nr, nc, cur.dist + 1, cur.path + dir[i]));
                }
            }
        }
        
        return "impossible";
    }
}

class Node implements Comparable<Node> {
    int r;
    int c;
    int dist;
    String path;
    
    Node (int r, int c, int dist, String path) {
        this.r = r;
        this.c = c;
        this.dist = dist;
        this.path = path;
    }
    
    @Override
    public int compareTo(Node o) {
        return path.compareTo(o.path);
    }
}