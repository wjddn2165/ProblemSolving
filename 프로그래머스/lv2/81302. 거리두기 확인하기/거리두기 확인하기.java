import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        int idx = 0;
        
        outer:for(String[] place : places) {
            int[][] board = new int[5][5];
            
            Stack<Node> stack = new Stack<>();
            
            for(int i=0;i<5;i++) {
                for(int j=0;j<5;j++) {
                    board[i][j] = place[i].charAt(j);
                    
                    if(board[i][j] == 'P') {
                        stack.push(new Node(i, j, 0));
                    }
                }
            }
            
            boolean[][] visited = new boolean[5][5];
            
            while(!stack.isEmpty()) {
                Node cur = stack.pop();
                
                if(cur.depth > 2) continue;
                // if(cur.depth == 0) visited = new boolean[5][5];
                
                visited[cur.r][cur.c] = true;
                
                if((cur.depth == 1 || cur.depth == 2) && (board[cur.r][cur.c] == 'P')) {
                    answer[idx++] = 0;
                    continue outer;
                }
            
                for(int i=0;i<4;i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];

                    if(nr < 0 || nc < 0 || nr >= 5 || nc >= 5) {
                        continue;
                    }

                    if(board[nr][nc] == 'X') {
                        continue;
                    }

                    if(visited[nr][nc]) {
                        continue;
                    }

                    stack.push(new Node(nr, nc, cur.depth + 1));
                }
            }
            
            answer[idx++] = 1;
        }
        
        return answer;
    }
}

class Node {
    int r, c, depth;
    
    Node(int r, int c, int depth) {
        this.r = r;
        this.c = c;
        this.depth = depth;
    }
}