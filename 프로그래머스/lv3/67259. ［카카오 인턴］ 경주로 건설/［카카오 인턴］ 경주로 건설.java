import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        int N = board.length;
        
        boolean[][][] visited = new boolean[N][N][4];
        
        // 북동남서
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        PriorityQueue<Car> queue = new PriorityQueue<>();
        queue.offer(new Car(0, 0, 0, 1));
        queue.offer(new Car(0, 0, 0, 2));
        
        while(!queue.isEmpty()) {
            Car cur = queue.remove();
            
            //System.out.println(cur.r + " " + cur.c + " " + cur.cost);
            
            if(cur.r == N - 1 && cur.c == N - 1) {
                answer = cur.cost;
                break;
            }
            
            visited[cur.r][cur.c][cur.dir] = true;
            
            int[] dir = {0, 1, 3};
            
            for(int i=0;i<dir.length;i++) {
                int nextDir = (cur.dir + dir[i]) % 4;
                
                int nr = cur.r + dr[nextDir];
                int nc = cur.c + dc[nextDir];
                
                if(nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }
                
                if(visited[nr][nc][nextDir]) {
                    continue;
                }
                
                if(board[nr][nc] == 1) {
                    continue;
                }
                
                if(i == 0) {
                    queue.offer(new Car(nr, nc, cur.cost + 100, nextDir));
                } else {
                    queue.offer(new Car(nr, nc, cur.cost + 600, nextDir));
                }
            }
        }
        
        return answer;
    }
}

class Car implements Comparable<Car> {
    int r, c;
    int cost;
    int dir;
    
    Car(int r, int c, int cost, int dir) {
        this.r = r;
        this.c = c;
        this.cost = cost;
        this.dir = dir;
    }
    
    @Override
    public int compareTo(Car o) {
        return cost - o.cost;
    }
}