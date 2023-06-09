class Solution {
    int MOD = 20170805;
    int[][][] dp;
    // 오른쪽, 아래
    int[] dr = {0, 1};
    int[] dc = {1, 0};
    int m;
    int n;
    
    public int solution(int m, int n, int[][] cityMap) {
        this.m = m;
        this.n = n;
        
        dp = new int[m][n][2];
        
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                for(int k=0;k<2;k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        
        return dfs(0, 0, 0, cityMap);
    }
    
    int dfs(int r, int c, int dir, int[][] cityMap) {
        if(r < 0 || c < 0 || r >= m || c >= n) {
            return 0;
        }
        
        if(r == m - 1 && c == n - 1) {
            return 1;
        }
        
        if(dp[r][c][dir] != -1) {
            return dp[r][c][dir];
        }
        
        dp[r][c][dir] = 0;
        
        if(cityMap[r][c] == 0) {
            dp[r][c][dir] += dfs(r, c + 1, 0, cityMap) % MOD;
            dp[r][c][dir] += dfs(r + 1, c, 1, cityMap) % MOD;
        } else if(cityMap[r][c] == 2) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            
            dp[r][c][dir] += dfs(nr, nc, dir, cityMap) % MOD;
        } else {
            return 0;
        }
        
        return dp[r][c][dir] % MOD;
    }
}