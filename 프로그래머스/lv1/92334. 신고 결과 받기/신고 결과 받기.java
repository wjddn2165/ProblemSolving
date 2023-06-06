import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, User> userMap = new HashMap<>();
        Map<String, Integer> reportCount = new HashMap<>();
        Map<String, List<String>> reportList = new HashMap<>();
        
        for(int i=0;i<id_list.length;i++) {
            userMap.put(id_list[i], new User(id_list[i]));
            reportList.put(id_list[i], new ArrayList<>());
        }
        
        Set<String> reportSet = new HashSet<>();
        
        for(int i=0;i<report.length;i++) {
            if(reportSet.contains(report[i])) continue;
            reportSet.add(report[i]);
            
            String[] s = report[i].split("\\s");
            String from = s[0];
            String to = s[1];

            reportCount.put(to, reportCount.getOrDefault(to, 0) + 1);
            reportList.get(to).add(from);
        }
        
        for(String name : reportCount.keySet()) {
            if(reportCount.get(name) >= k) {
                for(String reportUser : reportList.get(name)) {
                    userMap.get(reportUser).mailCount++;
                }
            }
        }
        
        int[] answer = new int[id_list.length];
        
        for(int i=0;i<id_list.length;i++) {
            answer[i] = userMap.get(id_list[i]).mailCount;
        }
        
        return answer;
    }
}

class User {
    String name;
    int mailCount;
    
    User(String name) {
        this.name = name;
        mailCount = 0;
    }
}