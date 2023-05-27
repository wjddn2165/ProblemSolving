import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
    
        Set<Integer> reserveSet = new HashSet<>();
        List<Integer> lostList = new ArrayList<>();
        
        for(int i=0;i<reserve.length;i++) {
            reserveSet.add(reserve[i]);
        }
        
        for(int i=0;i<lost.length;i++) {
            // 여별의 체육복을 가져온 학생이 도난당한 경우
            if(reserveSet.contains(lost[i])) {
                reserveSet.remove(lost[i]);
            } else {
                lostList.add(lost[i]);
            }
        }
        
        // 오름차순 정렬
        lostList.sort(null);
        
        int answer = n - lostList.size();
        
        for(int i=0;i<lostList.size();i++) {
            int num = lostList.get(i);
            
            if(reserveSet.contains(num - 1)) {
                answer++;
            } else if(reserveSet.contains(num + 1)) {
                answer++;
                reserveSet.remove(num + 1);
            }
        }
        
        return answer;
    }
}