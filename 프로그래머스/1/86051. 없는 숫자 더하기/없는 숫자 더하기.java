import java.util.stream.*;
import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<numbers.length;i++) {
            set.add(numbers[i]);
        }
        
        int sum = 0;
        
        for(int i=0;i<10;i++) {
            if(!set.contains(i)) {
                sum += i;
            }
        }
        
        return sum;
    }
}