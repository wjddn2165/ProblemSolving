import java.util.stream.*;
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        return Arrays.stream(commands).mapToInt(i -> {
            return IntStream.of(array)
                .skip(i[0] - 1)
                .limit(i[1] - i[0] + 1)
                .sorted()
                .toArray()[i[2] - 1];
        }).toArray();
    }
}