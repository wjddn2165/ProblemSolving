import java.util.*;

class Solution {
    Map<Integer, Map<String, Integer>> map;
    Set<Integer> set;
    
    public String[] solution(String[] orders, int[] course) {
        map = new HashMap<>();
        set = new HashSet<>();
        
        for(int i=0;i<course.length;i++) {
            map.put(course[i], new HashMap<>());
            set.add(course[i]);
        }
        
        for(String order : orders) {
            char[] temp = order.toCharArray();
            Arrays.sort(temp);
            dfs(new String(temp), new StringBuilder(), 0);
        }
        
        List<String> result = new ArrayList<>();
        
        for(int count : set) {
            int max = -1;
            
            Map<String, Integer> temp = map.get(count);
            
            for(int next : temp.values()) {
                max = Math.max(max, next);
            }
            
            if(max < 2) {
                break;
            }
            
            for(String key : temp.keySet()) {
                if(temp.get(key) == max) {
                    result.add(key);
                }
            }
        }
        
        result.sort(null);
        return result.toArray(new String[0]);
    }
    
    void dfs(String order, StringBuilder sb, int start) {
        int len = sb.length();
        
        if(set.contains(len)) {
            String comb = sb.toString();
            int count = map.get(len).getOrDefault(comb, 0) + 1;
            map.get(len).put(comb, count);
        }
        
        for(int i=start;i<order.length();i++) {
            sb.append(order.charAt(i));
            dfs(order, sb, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
}