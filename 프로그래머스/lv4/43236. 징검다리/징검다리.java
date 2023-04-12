import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int lo = 0;
        int hi = distance;
    
        Arrays.sort(rocks);
        
        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            if(decide(distance, rocks, n, mid)) lo = mid + 1;
            else hi = mid - 1;
        }
        return hi;
    }
    
    boolean decide(int distance, int[] rocks, int n, int k) {
        int pass = 0;
        int temp = 0;
        
        for(int i=0;i<rocks.length;i++) {
            if(rocks[i] - temp >= k) {
                temp = rocks[i];
            } else if(++pass > n) return false;
        }
        
        // 테스트 케이스가 좀 부족한듯..
        // if(distance - temp < k) return false;
        return true;
    }
}