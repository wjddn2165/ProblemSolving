import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        
        if(s.length() == 1) {
            return s.length();
        }
        
        for(int i=1;i<=s.length() / 2;i++) {
            answer = Math.min(answer, compress(s, i));
        }
        
        return answer;
    }
    
    int compress(String str, int unit) {
        List<String> subString = new ArrayList<>();
        
        int size = str.length();
        
        for(int i=0;i<size;i+=unit) {
            if(i + unit - 1 < size)
                subString.add(str.substring(i, i + unit));
            else
                subString.add(str.substring(i));
        }
        
        Stack<Node> stack = new Stack<>();
        
        for(int i=0;i<subString.size();i++) {
            if(!stack.isEmpty() && stack.peek().str.equals(subString.get(i))) {
                Node top = stack.pop();
                top.count += 1;
                stack.push(top);
            } else {
                stack.push(new Node(subString.get(i)));
            }
        }
        
        int sum = 0;
        
        for(int i=0;i<stack.size();i++) {
            Node cur = stack.get(i);
            sum += cur.str.length();
            
            if(cur.count == 1) continue;
            
            sum += String.valueOf(cur.count).length();
        }
        
        return sum;
    }
}

class Node {
    String str;
    int count;
    
    Node(String str) {
        this.str = str;
        count = 1;
    }
}