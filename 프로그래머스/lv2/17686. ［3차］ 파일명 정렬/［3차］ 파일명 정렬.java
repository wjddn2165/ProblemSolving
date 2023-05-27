import java.util.*;
import java.util.regex.*;

class Solution {
    public String[] solution(String[] files) {
        
        List<File> list = new ArrayList<>();
        
        for(String file : files) {
            String pattern = "^(\\D+)(\\d+)(.*)$";

            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(file);

            if (matcher.find()) {
                String head = matcher.group(1);
                String number = matcher.group(2);
                String tail = matcher.group(3);
                
                list.add(new File(file, head, number, tail));
            }
        }
        
        list.sort(null);
        
        String[] answer = new String[list.size()];
        
        for(int i=0;i<answer.length;i++) {
            answer[i] = list.get(i).name;
        }
        
        return answer;
    }
}

class File implements Comparable<File> {
    String name;
    
    String head;
    int number;
    String tail;
    
    File(String name, String head, String number, String tail) {
        this.name = name;
        this.head = head.toLowerCase();
        this.number = Integer.parseInt(number);
        this.tail = tail;
    }
    
    @Override
    public int compareTo(File o) {
        if(head.compareTo(o.head) == 0) {
            return number - o.number;
        }
        
        return head.compareTo(o.head);
    }
}