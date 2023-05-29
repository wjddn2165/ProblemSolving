import java.util.*;

class Solution {
    public String solution(String p) {
        return dfs(p);
    }
    
    String dfs(String str) {
        if(str.length() == 0) return str;
        
        int left = 0;
        int right = 0;
        
        String u = "";
        String v = "";
        
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i) == '(') left ++;
            else right ++;
            
            if(left == right) {
                u = str.substring(0, i + 1);
                v = str.substring(i + 1);
                break;
            }
        }
        
        Stack<Character> stack = new Stack<>();
        
        for(int i=0;i<u.length();i++) {
            char next = u.charAt(i);
            
            if(next == '(') stack.push(next);
            else if(!stack.isEmpty() && stack.peek() != next) {
                stack.pop();
            } else {
                stack.push(next);
                break;
            };
        }
        
        if(stack.isEmpty()) {
            return u + dfs(v);
        }
        
        String temp = u.substring(1, u.length() - 1);
        String newU = "";
        
        for(int i=0;i<temp.length();i++) {
            if(temp.charAt(i) == '(') newU += ")";
            else newU += "(";
        }
        
        return "(" + dfs(v) + ")" + newU;
    }
}