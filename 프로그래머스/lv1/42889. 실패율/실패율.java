import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {        
        Map<Integer, Stage> map = new HashMap<>();
        
        if(N == 1) return new int[]{1};
        
        for(int i=1;i<=N;i++) {
            map.put(i, new Stage(i));
        }
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for(int i=0;i<stages.length;i++) {
            queue.offer(stages[i]);
        }
        
        while(!queue.isEmpty()) {
            int stage = queue.remove();
            
            if(stage > N) continue;
            
            int clear = queue.size() + 1;
            int fail = 1;
            
            while(!queue.isEmpty() && queue.peek() == stage) {
                queue.remove();
                fail++;
            }
            
            map.get(stage).fail = (double)fail / clear;
            // System.out.println(stage + " " + fail);
        }
        
        List<Stage> list = new ArrayList<>();
        for(Stage stage : map.values()) {
            list.add(stage);
        }
        
        list.sort(null);
        
        int[] answer = new int[N];
        
        for(int i=0;i<N;i++) {
            answer[i] = list.get(i).num;
        }
        
        return answer;
    }
}

class Stage implements Comparable<Stage> {
    int num;
    double fail;
    
    Stage(int num) {
        this.num = num;
        this.fail = 0;
    }
    
    @Override
    public int compareTo(Stage o) {
        if(Double.compare(fail, o.fail) == 0) {
            return num - o.num;
        }
        
        return Double.compare(o.fail, fail);
    }
}