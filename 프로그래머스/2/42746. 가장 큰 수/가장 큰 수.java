import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<numbers.length;i++) {
            list.add(numbers[i]);
        }
        
        Collections.sort(list, new Sort());
        
        for(int i=0;i<list.size();i++) {
            sb.append(list.get(i));    
        }
        
        if(sb.charAt(0) == '0') return "0";
        return sb.toString();
    }
}

class Sort implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        String num1 = Integer.toString(o1);
        String num2 = Integer.toString(o2);
        
        return Integer.valueOf(num2 + num1) - Integer.valueOf(num1 + num2);
    }
}