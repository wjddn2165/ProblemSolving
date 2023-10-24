import java.util.stream.*;

class Solution {
    public double solution(int[] numbers) {
        return IntStream.of(numbers).average().orElse(0);
    }
}