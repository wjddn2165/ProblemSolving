import java.util.regex.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        
        int intPlayTime = timeToInt(play_time);
        // +1를 한 이유는 배열이 0부터 시작하기 때문에
        int[] prefixSum = new int[intPlayTime + 1];
        
        for(String log : logs) {
            String[] s = log.split("-");
            
            int start = timeToInt(s[0]);
            int end = timeToInt(s[1]);
            
            // 시청을 시작한 시간은 +1, 마지막으로 시청한 시간에서 -1을 해줌
            prefixSum[start]++;
            prefixSum[end]--;
        }
        
        // +1, -1로 기록했던 시청 기록을 누적합하여 누적 시청 시간으로 변경
        for(int i=1;i<prefixSum.length;i++) {
            prefixSum[i] += prefixSum[i - 1];
        }
        
        int intAdvTime = timeToInt(adv_time);
        
        int left = 0;
        int right = 1;
        
        long sum = prefixSum[0];
        long max = 0;
        
        // max가 갱신될때의 left 값
        int result = 0;
        
        while(right < prefixSum.length) {
            if(right - left < intAdvTime) {
                sum += prefixSum[right++];
            }
            
            if(right - left == intAdvTime) {
                if(sum > max) {
                    // System.out.println(sum);
                    max = sum;
                    result = left;
                }
                
                sum -= prefixSum[left++];
            }
        }
        
//         System.out.println(intPlayTime);
//         System.out.println(intAdvTime);
        
//         System.out.println(result);
        return intToTime(result);
    }
    
    int timeToInt(String time) {
        String[] s = time.split(":");
        int hour = Integer.parseInt(s[0]);
        int min = Integer.parseInt(s[1]);
        int sec = Integer.parseInt(s[2]);
        
        return hour * 3600 + min * 60 + sec;
    }
    
    String intToTime(int intTime) {
        int hour = (intTime / 3600);
        intTime %= 3600;
        int min = (intTime / 60);
        int sec = (intTime % 60);
        
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
}