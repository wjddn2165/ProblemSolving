import java.util.*;

class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        boolean[][] visited = new boolean[m][n];
        
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(picture[i][j] == 0 || visited[i][j]) continue;
                
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(i, j, picture, visited));
                numberOfArea++;
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    int bfs(int r, int c, int[][] picture, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;
        
        int count = 1;
        int color = picture[r][c];
        
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        int m = picture.length;
        int n = picture[0].length;
        
        while(!queue.isEmpty()) {
            int[] cur = queue.remove();
            
            for(int i=0;i<4;i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                
                if(nr < 0 || nc < 0 || nr >= m || nc >= n) {
                    continue;
                }
                
                if(picture[nr][nc] != color) {
                    continue;
                }
                
                if(visited[nr][nc]) {
                    continue;
                }
                
                queue.offer(new int[]{nr, nc});
                visited[nr][nc] = true;
                count++;
            }
        }
        
        return count;
    }
}