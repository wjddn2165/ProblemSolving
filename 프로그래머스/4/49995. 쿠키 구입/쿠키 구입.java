class Solution {
    public int solution(int[] cookie) {
        int n = cookie.length;
        int answer = 0;
        
        for(int i=0;i<n - 1;i++) {
            int leftSum = cookie[i];
            int rightSum = 0;
            
            int left = i;
            int right = i;
            
            while(true) {
                if(leftSum >= rightSum) {
                    right ++;
                    if(right == n) break;
                    rightSum += cookie[right];
                } else if(rightSum > leftSum) {
                    left --;
                    if(left == -1) break;
                    leftSum += cookie[left];
                }
                
                if(leftSum == rightSum) {
                    answer = Math.max(answer, leftSum);
                }
            }
        }
        
        return answer;
    }
}