import java.util.*;

class Solution {
    boolean[] visited = new boolean[11];
    int maxScore = 0;
    
    void dfs(int score, int n, int[] info, int[] arrow, int[] result) {
        if(score > maxScore) {
            maxScore = score;
            
            for(int i=0;i<11;i++) {
                result[i] = arrow[i];
            }
            
            if(n > 0) {
                result[10] += n;
            }
        }
        
        for(int i=0;i<11;i++) {
            if(!visited[i] && info[10 - i] < n) {
                if(info[10 - i] == 0) {
                    visited[i] = true;
                    arrow[10 - i] += info[10 - i] + 1;
                    dfs(score + i, n - info[10 - i] - 1, info, arrow, result);
                    arrow[10 - i] -= info[10 - i] + 1;
                    visited[i] = false;
                } else {
                    visited[i] = true;
                    arrow[10 - i] += info[10 - i] + 1;
                    dfs(score + 2 * i, n - info[10 - i] - 1, info, arrow, result);
                    arrow[10 - i] -= info[10 - i] + 1;
                    visited[i] = false;
                }
            }
        }
    }
    
    public int[] solution(int n, int[] info) {
        int[] arrow = new int[11];
        int[] result = new int[11];
        
        int initScore = 0;
        
        // 라이언의 점수를 처음 - (어피치의 점수)로 설정.
        for(int i=0;i<11;i++) {
            if(info[i] > 0) {
                initScore -= 10 - i;
            }
        }
                
        dfs(initScore, n, info, arrow, result);
        
        if(maxScore == 0) {
            return new int[]{-1};
        }
        
        return result;
    }
}
