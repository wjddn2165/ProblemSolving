import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int N = board.length;
        
        boolean[][][][] visited = new boolean[N][N][N][N];
        
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        Queue<Node> queue = new LinkedList<>();
        
        queue.offer(new Node(0, 0, 0, 1, 0));
        visited[0][0][0][1] = true;
        visited[0][1][0][0] = true;
        
        while(!queue.isEmpty()) {
            Node cur = queue.remove();
            
            if((cur.r1 == N - 1 && cur.c1 == N - 1) || (cur.r2 == N - 1 && cur.c2 == N - 1)) {
                return cur.depth;
            }
            
            for(int i=0;i<4;i++) {
                int nr1 = cur.r1 + dr[i];
                int nc1 = cur.c1 + dc[i];
                int nr2 = cur.r2 + dr[i];
                int nc2 = cur.c2 + dc[i];
                
                if(nr1 < 0 || nc1 < 0 || nr2 < 0 || nc2 < 0 ||
                  nr1 >= N || nc1 >= N || nr2 >= N || nc2 >= N) continue;
                
                if(board[nr1][nc1] == 1 || board[nr2][nc2] == 1) continue;
                
                if(!visited[nr1][nc1][nr2][nc2]) {
                    queue.offer(new Node(nr1, nc1, nr2, nc2, cur.depth + 1));
                    visited[nr1][nc1][nr2][nc2] = true;
                    visited[nr2][nc2][nr1][nc1] = true;
                }
                
                // 드론이 수평하게 있는 경우
                if(cur.r1 == cur.r2) {
                    // 북쪽 회전
                    if(i == 0) {
                        // r1 c1 축으로 회전
                        if(!visited[cur.r1][cur.c1][cur.r1 - 1][cur.c1]) {
                            queue.offer(new Node(cur.r1, cur.c1, cur.r1 - 1, cur.c1,cur. depth + 1));
                            visited[cur.r1][cur.c1][cur.r1 - 1][cur.c1] = true;
                            visited[cur.r1 - 1][cur.c1][cur.r1][cur.c1] = true;
                        }
                        
                        // r2 c2 축으로 회전
                        if(!visited[cur.r2 - 1][cur.c2][cur.r2][cur.c2]) {
                            queue.offer(new Node(cur.r2 - 1, cur.c2, cur.r2, cur.c2,cur. depth + 1));
                            visited[cur.r2 - 1][cur.c2][cur.r2][cur.c2] = true;
                            visited[cur.r2][cur.c2][cur.r2 - 1][cur.c2] = true;
                        }
                    }
                    
                    // 남쪽 회전
                    if(i == 2) {
                        // r1 c1 축으로 회전
                        if(!visited[cur.r1][cur.c1][cur.r1 + 1][cur.c1]) {
                            queue.offer(new Node(cur.r1, cur.c1, cur.r1 + 1, cur.c1,cur. depth + 1));
                            visited[cur.r1][cur.c1][cur.r1 + 1][cur.c1] = true;
                            visited[cur.r1 + 1][cur.c1][cur.r1][cur.c1] = true;
                        }
                        
                        // r2 c2 축으로 회전
                        if(!visited[cur.r2 + 1][cur.c2][cur.r2][cur.c2]) {
                            queue.offer(new Node(cur.r2 + 1, cur.c2, cur.r2, cur.c2,cur. depth + 1));
                            visited[cur.r2 + 1][cur.c2][cur.r2][cur.c2] = true;
                            visited[cur.r2][cur.c2][cur.r2 + 1][cur.c2] = true;
                        }
                    }
                }
                
                // 드론이 수직하게 있는 경우
                if(cur.c1 == cur.c2) {
                    // 동쪽 회전
                    if(i == 1) {
                        // r1 c1 축으로 회전
                        if(!visited[cur.r1][cur.c1][cur.r1][cur.c1 + 1]) {
                            queue.offer(new Node(cur.r1, cur.c1, cur.r1, cur.c1 + 1,cur. depth + 1));
                            visited[cur.r1][cur.c1][cur.r1][cur.c1 + 1] = true;
                            visited[cur.r1][cur.c1 + 1][cur.r1][cur.c1] = true;
                        }
                        
                        // r2 c2 축으로 회전
                        if(!visited[cur.r2][cur.c2 + 1][cur.r2][cur.c2]) {
                            queue.offer(new Node(cur.r2, cur.c2 + 1, cur.r2, cur.c2,cur. depth + 1));
                            visited[cur.r2][cur.c2 + 1][cur.r2][cur.c2] = true;
                            visited[cur.r2][cur.c2][cur.r2][cur.c2 + 1] = true;
                        }
                    }
                    
                    // 서쪽 회전
                    if(i == 3) {
                        // r1 c1 축으로 회전
                        if(!visited[cur.r1][cur.c1][cur.r1][cur.c1 - 1]) {
                            queue.offer(new Node(cur.r1, cur.c1, cur.r1, cur.c1 - 1, cur. depth + 1));
                            visited[cur.r1][cur.c1][cur.r1][cur.c1 - 1] = true;
                            visited[cur.r1][cur.c1 - 1][cur.r1][cur.c1] = true;
                        }
                        
                        // r2 c2 축으로 회전
                        if(!visited[cur.r2][cur.c2 - 1][cur.r2][cur.c2]) {
                            queue.offer(new Node(cur.r2, cur.c2 - 1, cur.r2, cur.c2, cur.depth + 1));
                            visited[cur.r2][cur.c2 - 1][cur.r2][cur.c2] = true;
                            visited[cur.r2][cur.c2][cur.r2][cur.c2 - 1] = true;
                        }
                    }
                }
            }
        }
        
        return -1;
    }
}

class Node {
    int r1, c1, r2, c2;
    int depth;
    
    Node(int r1, int c1, int r2, int c2, int depth) {
        this.r1 = r1;
        this.c1 = c1;
        this.r2 = r2;
        this.c2 = c2;
        
        this.depth = depth;
    }
}