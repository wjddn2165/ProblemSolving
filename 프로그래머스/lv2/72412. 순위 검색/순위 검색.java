import java.util.*;

class Solution {
    Map<String, List<Integer>> map;
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        map = new HashMap<>();
        
        for(int i=0;i<info.length;i++) {
            dfs(info[i].split("\\s"), "", 0);
        }
        
        for(String key : map.keySet()) {
            map.get(key).sort(null);
        }
        
        for(int i=0;i<query.length;i++) {
            String[] split = query[i]
                .replaceAll("\\sand\\s", "")
                .split("\\s");
            
            String key = split[0];
            int score = Integer.parseInt(split[1]);

            // System.out.println(score);
            
            List<Integer> list = map.get(key);
            
            if(list == null) {
                answer[i] = 0;
                continue;
            }
            
            answer[i] = findCount(list, score);
        }
        
        return answer;
    }
    
    void dfs(String[] info, String cur, int depth) {
        if(depth == 4) {
            int score = Integer.parseInt(info[4]);
            
            if(!map.containsKey(cur)) {
                map.put(cur, new ArrayList<>());
            } 
            
            map.get(cur).add(score);
            return;
        }
        
        dfs(info, cur + info[depth], depth + 1);
        dfs(info, cur + "-", depth + 1);
    }
    
    int findCount(List<Integer> list, int score) {
        int lo = 0;
        int hi = list.size() - 1;
        
        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if(list.get(mid) >= score) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        
        return list.size() - hi - 1; 
    }
}