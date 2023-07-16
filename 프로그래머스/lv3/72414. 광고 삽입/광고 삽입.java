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
        String regExp = "(\\d\\d):(\\d\\d):(\\d\\d)";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(time);
        
        matcher.find();
        
        int hour = Integer.parseInt(matcher.group(1));
        int min = Integer.parseInt(matcher.group(2));
        int sec = Integer.parseInt(matcher.group(3));
        
        return hour * 3600 + min * 60 + sec;
    }
    
    String intToTime(int intTime) {
        String hour = String.valueOf(intTime / 3600);
        intTime %= 3600;
        String min = String.valueOf(intTime / 60);
        String sec = String.valueOf(intTime % 60);
        
        if(hour.length() == 1) {
            hour = "0" + hour;
        }
        
        if(min.length() == 1) {
            min = "0" + min;
        }
        
        if(sec.length() == 1) {
            sec = "0" + sec;
        }
        
        return hour + ":" + min + ":" + sec;
    }
}