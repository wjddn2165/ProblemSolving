class Solution {
    public long solution(int[] sequence) {
        long answer = -100000l;
        
        if(sequence.length == 1) {
            return Math.abs(sequence[0]);
        }
        
        long[][] dp = new long[sequence.length][2];
        
        // -1, 1, -1 ...
        dp[0][0] = -(long)sequence[0];
        
        // 1, -1, 1 ...
        dp[0][1] = (long)sequence[0];
        
        int flag = 1;
        
        for(int i=1;i<sequence.length;i++) {
            dp[i][0] = Math.max(sequence[i] * flag, dp[i-1][0] + sequence[i] * flag);
            flag *= -1;
            dp[i][1] = Math.max(sequence[i] * flag, dp[i-1][1] + sequence[i] * flag);
            
            answer = Math.max(Math.max(dp[i][0], dp[i][1]), answer);
        }
        
        return answer;
    }
}