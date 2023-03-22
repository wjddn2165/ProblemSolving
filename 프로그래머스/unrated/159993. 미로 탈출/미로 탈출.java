import java.util.*;

class Solution {
    
    boolean impossible = false;
    
    int bfs(Point src, Point dest, String[] maps) {
        
        int row = maps.length;
        int col = maps[0].length();
        
        boolean[][] visited = new boolean[row][col];
        
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(src, 0));
        visited[src.r][src.c] = true;
        
        while(!queue.isEmpty()) {
            Node node = queue.remove();
            Point cur = node.point;
            
            if(cur.equals(dest)) {
                return node.time;
            }
            
            for(int i=0;i<4;i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                
                if(nr >= 0 && nr < row && nc >= 0 && nc < col) {
                    if(!visited[nr][nc] && maps[nr].charAt(nc) != 'X') {
                        queue.offer(new Node(new Point(nr, nc), node.time + 1));
                        visited[nr][nc] = true;
                    }
                }
            }
        }
        
        impossible = true;
        return -1;
    }
    
    public int solution(String[] maps) {
        int answer = 0;
        
        int row = maps.length;
        int col = maps[0].length();
        
        Point S = null;
        Point E = null;
        Point L = null;
        
        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                if(maps[i].charAt(j) == 'S') {
                    S = new Point(i, j);
                }
                
                if(maps[i].charAt(j) == 'E') {
                    E = new Point(i, j);
                }
                
                if(maps[i].charAt(j) == 'L') {
                    L = new Point(i, j);
                }
            }
        }
        
        
        answer += bfs(S, L, maps);
        answer += bfs(L, E, maps);
        
        if(impossible) {
            return -1;
        }
        
        return answer;
    }
}

class Point {
    int r;
    int c;
    
    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
    
    @Override
    public boolean equals(Object o){
        Point p = (Point) o;
        if(r == p.r && c == p.c) {
            return true;
        }
        
        return false;
    }
}

class Node {
    Point point;
    int time;
    
    Node(Point point, int time) {
        this.point = point;
        this.time = time;
    }
} 