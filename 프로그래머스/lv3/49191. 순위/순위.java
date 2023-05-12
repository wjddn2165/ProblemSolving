class Solution {
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        int[][] dp = new int[n][n];
        
        for(int[] result : results) {
            dp[result[0] - 1][result[1] - 1] = 1;
            dp[result[1] - 1][result[0] - 1] = -1;
        }
        
        for(int k=0;k<n;k++) {
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    if(dp[i][k] == 1 && dp[k][j] == 1) {
                        dp[i][j] = 1;
                        dp[j][i] = -1;
                    }
                }
            }
        }
        
        for(int i=0;i<n;i++) {
            int count = 0;
            for(int j=0;j<n;j++) {
                if(dp[i][j] != 0) count++;
            }
            
            if(count == n - 1) answer++;
        }
        
        return answer;
    }
}