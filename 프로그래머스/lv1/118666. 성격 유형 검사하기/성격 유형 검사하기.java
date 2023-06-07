import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> map = new HashMap<>();
        char[] types = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        
        for(int i=0;i<types.length;i++) {
            map.put(types[i], 0);
        }
        
        for(int i=0;i<survey.length;i++) {
            map.put(survey[i].charAt(0), map.get(survey[i].charAt(0)) + 4 - choices[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<types.length;i+=2) {
            int first = map.get(types[i]);
            int second = map.get(types[i + 1]);
            
            if(first == second) {
                sb.append((char)Math.min((int) types[i], (int) types[i + 1]));
                continue;
            }
            
            sb.append((first > second) ? types[i] : types[i + 1]);
        }
        
        return sb.toString();
    }
}