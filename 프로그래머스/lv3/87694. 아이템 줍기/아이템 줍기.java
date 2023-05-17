import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        
        int[][] board = new int[101][101];
        
        for(int[] rect : rectangle) {
            for(int i=rect[1] * 2;i<=rect[3] * 2;i++) {
                for(int j=rect[0] * 2;j<=rect[2] * 2;j++) {
                    if(rect[1] * 2 == i || i == rect[3] * 2 || rect[0] * 2 == j || j == rect[2] * 2) {
                        board[i][j] = 1;
                    }
                }
            }
        }
        
        for(int[] rect : rectangle) {
            for(int i=rect[1] * 2;i<=rect[3] * 2;i++) {
                for(int j=rect[0] * 2;j<=rect[2] * 2;j++) {
                    if((rect[1] * 2 < i && i < rect[3] * 2) && (rect[0] * 2 < j && j < rect[2] * 2)) {
                        board[i][j] = 0;
                    }
                }
            }
        }
        
        boolean[][] visited = new boolean[101][101];
        
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(characterY * 2, characterX * 2, 0));
        visited[characterY * 2][characterX * 2] = true;
    
        
        while(!queue.isEmpty()) {
            Node cur = queue.remove();
            
            if(cur.row == itemY * 2 && cur.col == itemX * 2) {
                return cur.dist / 2;
            }
            
            for(int i=0;i<4;i++) {
                int nr = cur.row + dr[i];
                int nc = cur.col + dc[i];
                
                if(nr < 0 || nc < 0 || nr > 100 || nc > 100) continue;
                
                if(!visited[nr][nc] && board[nr][nc] == 1) {
                    queue.offer(new Node(nr, nc, cur.dist + 1));
                    visited[nr][nc] = true;
                }
            }
        }
        
        
        return answer;
    }
}

class Node {
    int row;
    int col;
    int dist;
    
    Node(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}