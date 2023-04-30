import java.util.*;

class Solution {
    final int INF = 987654321;
    
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;
        
        List<int[]> list = new ArrayList<>();
        
        for(int i=0;i<problems.length;i++) {
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
            
            list.add(problems[i]);
        }
        
        maxAlp = Math.max(alp, maxAlp);
        maxCop = Math.max(cop, maxCop);
        
        list.add(new int[]{0, 0, 1, 0, 1});
        list.add(new int[]{0, 0, 0, 1, 1});
        
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        
        for(int i=alp;i<=maxAlp;i++) {
            for(int j=cop;j<=maxCop;j++) {
                dp[i][j] = INF;
            }
        }
        
        dp[alp][cop] = 0;
        
        for(int i=alp;i<=maxAlp;i++) {
            for(int j=cop;j<=maxCop;j++) {
                
                if(i == maxAlp && j == maxCop) {
                    return dp[i][j];
                }
                
                for(int k=0;k<list.size();k++) {
                    int[] problem = list.get(k);
                    
                    if(problem[0] <= i && problem[1] <= j) {
                        
                        int nextAlp = Math.min(i + problem[2], maxAlp);
                        int nextCop = Math.min(j + problem[3], maxCop);
                        
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + problem[4]);
                    }
                }
            }
        }
        
        return dp[maxAlp][maxCop];
    }
}