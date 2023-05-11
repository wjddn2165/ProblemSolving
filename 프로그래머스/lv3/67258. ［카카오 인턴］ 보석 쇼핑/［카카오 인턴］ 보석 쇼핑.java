import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};
        
        List<int[]> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
    
        for(int i=0;i<gems.length;i++) {
            set.add(gems[i]);
        }
        
        int total = set.size();
        
        set.clear();
        
        Map<String, Integer> map = new HashMap<>();
        
        for(int left = 0, right = 0; right < gems.length; right++) {
            //update hi
            map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
            set.add(gems[right]);
            
            //update lo
            while(total == set.size()) {
                result.add(new int[]{left + 1, right + 1});

                map.put(gems[left], map.get(gems[left]) - 1);
                if(map.get(gems[left]) == 0) {
                    set.remove(gems[left]);
                }
                
                left++;
            }
        }
        
        result.sort((a, b) -> (a[1] - a[0] == b[1] - b[0]) ? a[0] - b[0] : a[1] - a[0] - (b[1] - b[0]));
        
        return result.get(0);
    }
}