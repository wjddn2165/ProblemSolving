class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        
        if(s.length() == 1) return 1;
        
        for(int i=1;i<=s.length()/2;i++) {
            answer = Math.min(answer, dfs(s, i, 1).length());
        }
        
        return answer;
    }
    
    String dfs(String str, int n, int repeat) {
        if(str.length() < n) return str;
        
        String result = "";
        String pre = str.substring(0, n);
        String post = str.substring(n);
        
        if(!post.startsWith(pre)) {
            if(repeat == 1) {
                return result += pre + dfs(post, n, 1);
            } else {
                return result += repeat + pre + dfs(post, n, 1);
            }
        }
        
        return result += dfs(post, n, repeat + 1);
    }
}