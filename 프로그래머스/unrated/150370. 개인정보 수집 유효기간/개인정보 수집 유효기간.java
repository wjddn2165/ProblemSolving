import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();
        
        for(String term : terms) {
            String[] s = term.split("\\s");
            termMap.put(s[0], Integer.parseInt(s[1]));
        }
        
        int todayInt = dayToInt(today);
        List<Integer> list = new ArrayList<>();
        
        int idx = 0;
        
        for(String privacy : privacies) {
            String[] s = privacy.split("\\s");
            int deadLine = dayToInt(s[0]) + 28 * termMap.get(s[1]) - 1;
            
            idx++;
            
            if(deadLine < todayInt) {
                list.add(idx);
            }
        }
        
        int[] answer = new int[list.size()];
        
        for(int i=0;i<answer.length;i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    int dayToInt(String str) {
        String[] s = str.split("\\.");
        int year = Integer.parseInt(s[0]);
        int month = Integer.parseInt(s[1]);
        int day = Integer.parseInt(s[2]);
        
        return year * 12 * 28 + month * 28 + day;
    }
}

