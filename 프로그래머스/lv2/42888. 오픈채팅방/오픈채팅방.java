import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        
        for(int i=0;i<record.length;i++) {
            String[] s = record[i].split("\\s");
            String op = s[0];
            String id = s[1];
            String name = null;
            
            if(s.length > 2) {
                name = s[2];
                map.put(id, name);
            }
            
            if(!op.equals("Change")) {
                list.add(id + " " + op);
            }
        }
        
        String[] answer = new String[list.size()];
        
        for(int i=0;i<list.size();i++) {
            String[] s = list.get(i).split("\\s");
            
            if(s[1].equals("Enter")) {
                answer[i] = map.get(s[0]) + "님이 들어왔습니다.";
            } else {
                answer[i] = map.get(s[0]) + "님이 나갔습니다.";
            }
        }
        
        return answer;
    }
}