import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        
        Deque<int[]> queue = new ArrayDeque<>();
        
        for(int i=0;i<stones.length;i++) {
            while(!queue.isEmpty() && queue.peekLast()[0] < stones[i]) {
                queue.removeLast();
            }
            
            queue.offer(new int[]{stones[i], i});
            
            if(queue.peek()[1] < i - k + 1) {
                queue.removeFirst();
            }
            
            if(i >= k - 1) {
                answer = Math.min(answer, queue.peek()[0]);
            }
        }

        return answer;
    }
}