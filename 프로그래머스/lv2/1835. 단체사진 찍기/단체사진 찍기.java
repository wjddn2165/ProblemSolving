import java.util.*;

class Solution {
    char[] ch = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    
    int answer;
    int n;
    String[] data;
    boolean[] visited;
    
    public int solution(int n, String[] data) {
        answer = 0;
        
        this.n = n;
        this.data = data;
        
        
        
        visited = new boolean[8];
        dfs(new HashMap<>());
        return answer;
    }
    
    void dfs(Map<Character, Integer> map) {
        for(int i=0;i<n;i++) {
            char a = data[i].charAt(0);
            char b = data[i].charAt(2);
            if(map.containsKey(a) && map.containsKey(b)) {
                char op = data[i].charAt(3);
                int num = data[i].charAt(4) - '0';
                int dist = Math.abs(map.get(a) - map.get(b)) - 1;
                
                //System.out.println(dist);
                
                if(op == '<') {
                    if(dist >= num) return;
                } else if(op == '>') {
                    if(dist <= num) return;
                } else {
                    if(dist != num) return;
                }
            }
        }
        
        int idx = map.size();
        
        if(idx == 8) {
            answer++;
            return;
        }
        
        for(int i=0;i<8;i++) {
            if(!visited[i]) {
                visited[i] = true;
                map.put(ch[i], idx);
                dfs(map);
                map.remove(ch[i]);
                visited[i] = false;
            }
        }
    }
}