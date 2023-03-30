import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, new Sort());
        int pos = routes[0][1];    
        int answer = 1;
    
        for(int i=1;i<routes.length;i++) {
            if(pos >= routes[i][0]) continue;
            answer++;
            pos = routes[i][1];
        }
        return answer;
    }
}

class Sort implements Comparator<int[]> {
    @Override
    public int compare(int[] o1, int[] o2) {
        return o1[1] - o2[1];
    }
}