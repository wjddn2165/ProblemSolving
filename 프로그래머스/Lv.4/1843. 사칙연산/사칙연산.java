import java.util.*;

class Solution {
    
    static final int INF = 987654321;
    
    public int solution(String arr[]) {
        int size = (arr.length + 1) / 2;
        int[][][] dp = new int[size][size][2];
        
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                dp[i][j][0] = INF;
                dp[i][j][1] = -INF;
            }
        }
        
        return dfs(0, size - 1, arr, dp)[1];
    }
    
    int calc(int op1, String sign, int op2) {
        if(sign.equals("+")) {
            return op1 + op2;
        } else {
            return op1 - op2;
        }
    }
    
    int[] dfs(int left, int right, String[] arr, int[][][] dp) {
        if(left == right) {
            int num = Integer.parseInt(arr[2 * left]);
            return new int[]{num, num};
        }
        
        if(dp[left][right][1] != -INF) {
            return dp[left][right];
        }
        
        for(int i=left;i<right;i++) {
            dp[left][right][1] = Math.max(dp[left][right][1],
                                  calc(dfs(left, i, arr, dp)[1], arr[i * 2 + 1], dfs(i + 1, right, arr, dp)[1]));
            
            dp[left][right][1] = Math.max(dp[left][right][1],
                                  calc(dfs(left, i, arr, dp)[1], arr[i * 2 + 1], dfs(i + 1, right, arr, dp)[0]));
            
            dp[left][right][0] = Math.min(dp[left][right][0],
                                  calc(dfs(left, i, arr, dp)[0], arr[i * 2 + 1], dfs(i + 1, right, arr, dp)[0]));
            
            dp[left][right][0] = Math.min(dp[left][right][0],
                                  calc(dfs(left, i, arr, dp)[0], arr[i * 2 + 1], dfs(i + 1, right, arr, dp)[1]));
        }
        
        return dp[left][right];
    }
}