class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();
        
        int num = 0;
        
        while(sb.length() < m * t) {
            sb.append(Integer.toString(num++ , n));
        }
        
        for(int i=0;i<t;i++) {
            result.append(sb.charAt(p - 1));
            p += m;
        }
        
        return result.toString().toUpperCase();
    }
}