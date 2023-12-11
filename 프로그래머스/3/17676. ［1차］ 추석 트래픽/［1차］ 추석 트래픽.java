import java.util.*;

class Solution {
    public int solution(String[] lines) {
        int n = lines.length;
        int[][] times = new int[n][2];
        
        for(int i=0;i<n;i++) {
            times[i] = timeToInt(lines[i]);
        }
        
        Arrays.sort(times, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        int max = 0;
        
        for(int i=0;i<n;i++) {            
            while(!queue.isEmpty() && queue.peek() <= times[i][0] - 1000) {
                queue.remove();
            }
            
            queue.offer(times[i][1]);
            max = Math.max(max, queue.size());
        }
        
        return max;
    }
    
    int[] timeToInt(String time) {
        String[] times = time.split(" ");
        String[] endTime = times[1].split("\\.");
        String[] temp = endTime[0].split(":");
        
        int endTimeSum = 0;
        endTimeSum += Integer.parseInt(temp[0]) * 60 * 60 * 1000;
        endTimeSum += Integer.parseInt(temp[1]) * 60 * 1000;
        endTimeSum += Integer.parseInt(temp[2]) * 1000;
        endTimeSum += Integer.parseInt(endTime[1]);
        
        String takeTime = times[2].substring(0, times[2].length() - 1);
        int startTime = endTimeSum - (int) (Float.parseFloat(takeTime) * 1000) + 1;
        
        return new int[]{startTime, endTimeSum};
    }
}