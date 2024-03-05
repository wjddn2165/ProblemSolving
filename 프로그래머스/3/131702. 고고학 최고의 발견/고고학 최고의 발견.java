import java.util.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n;
    
    public int solution(int[][] clockHands) {
        n = clockHands.length;
        dfs(new ArrayList<>(), clockHands);
        return answer;
    }
    
    void dfs(List<Integer> list, int[][] clockHands) {
        if(list.size() == n) {
            int sum = 0;
            int[][] newBoard = new int[n][n];
            
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    newBoard[i][j] = clockHands[i][j];
                }
            }
            
            for(int i=0;i<n;i++) {
                int count = list.get(i);
                sum += count;
                flip(newBoard, 0, i, count);
            }
            
            for(int i=1;i<n;i++) {
                for(int j=0;j<n;j++) {
                    if(newBoard[i - 1][j] != 0) {
                        sum += 4 - newBoard[i - 1][j];
                        flip(newBoard, i, j, 4 - newBoard[i - 1][j]);
                    }
                }
            }
            
            if(check(newBoard)) {
                answer = Math.min(answer, sum);
            }
            
            return;
        }
        
        for(int i=0;i<4;i++) {
            list.add(i);
            dfs(list, clockHands);
            list.remove(list.size() - 1);
        }
    }
    
    void flip(int[][] board, int r, int c, int count) {
        for(int i=0;i<4;i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if(nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
            
            board[nr][nc] += count;
            board[nr][nc] %= 4;
        }
        
        board[r][c] += count;
        board[r][c] %= 4;
    }
    
    boolean check(int[][] board) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(board[i][j] != 0) return false;
            }
        }
        
        return true;
    }
}