class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        
        int left = 0;
        int right = 0;
        
        for(int i=0;i<goal.length;i++) {
            
            if(left < cards1.length && cards1[left].equals(goal[i])) {
                left++;
                continue;
            }
            
            if(right < cards2.length && cards2[right].equals(goal[i])) {
                right++;
                continue;
            }
            
            return "No";
        }
        
        return "Yes";
    }
}