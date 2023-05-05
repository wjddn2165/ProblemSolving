import java.util.*;

class Solution {
    
    // n명이 다리를 건널 수 없으면 true 반환
    boolean decide(int n, int k, int[] stones) {
        
        int count = 0;
        
        for(int i=0;i<stones.length;i++) {
            if(stones[i] - n <= 0) {
                count ++;
            } else {
                count = 0;
            }
            
            if(count == k) return true;
        }
        
        return false;
    }
    
    public int solution(int[] stones, int k) {
        int lo = 1;
        int hi = 2000000000;
        
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            
            if(decide(mid, k, stones)) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        
        return lo;
    }
}