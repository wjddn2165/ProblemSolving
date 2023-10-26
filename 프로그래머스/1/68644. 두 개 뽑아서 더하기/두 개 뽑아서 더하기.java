import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        List<Integer> list = new ArrayList<>();
        dfs(0,0,0,list,numbers);
        list.sort(null);
        int[] answer = new int[list.size()];
        for(int i=0;i<list.size();i++) answer[i] = list.get(i);
        return answer;
    }
    
    void dfs(int depth, int start, int sum, List<Integer> list, int[] numbers) {
        if(depth == 2) {
            if(!list.contains(sum))
                list.add(sum);
            return;
        }
        
        for(int i=start;i<numbers.length;i++) {
            dfs(depth + 1, i + 1, sum + numbers[i], list, numbers);
        }
    }
    
}