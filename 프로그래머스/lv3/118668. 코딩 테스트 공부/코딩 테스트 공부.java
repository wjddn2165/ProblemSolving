import java.util.*;

class Solution {
    final int INF = 987654321;
    
    int maxAlp = 0;
    int maxCop = 0;
    
    int[][] dp;
    
    int dfs(int alp, int cop, List<int[]> list) {
        if(alp == maxAlp && cop == maxCop) {
            return 0;
        }
        
        if(dp[alp][cop] != INF) {
            return dp[alp][cop];
        }
        
        dp[alp][cop] = INF + 1;
                
        for(int i=0;i<list.size();i++) {
            int[] problem = list.get(i);
            
            if(alp >= problem[0] && cop >= problem[1]) {
                int nextAlp = Math.min(alp + problem[2], maxAlp);
                int nextCop = Math.min(cop + problem[3], maxCop);
                
                dp[alp][cop] = Math.min(dp[alp][cop],
                                    dfs(nextAlp, nextCop, list) + problem[4]);
            }
        }
        
        return dp[alp][cop];
    }
    
    public int solution(int alp, int cop, int[][] problems) {
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
        
        dp = new int[maxAlp + 1][maxCop + 1];
        
        for(int i=alp;i<=maxAlp;i++) {
            for(int j=cop;j<=maxCop;j++) {
                dp[i][j] = INF;
            }
        }
        
        return dfs(alp, cop, list);
    }
}