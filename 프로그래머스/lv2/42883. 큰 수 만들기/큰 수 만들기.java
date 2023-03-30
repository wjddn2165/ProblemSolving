class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder(number);
        
        outer: for(int i=0;i<k;i++) {
            
            int size = sb.length();
            
            for(int j=0;j<size - 1;j++) {
                if(sb.charAt(j) < sb.charAt(j + 1)) {
                    sb.deleteCharAt(j);
                    continue outer;
                }
            }
            
            sb.deleteCharAt(size - 1);
        }
        
        return sb.toString();
    }
}