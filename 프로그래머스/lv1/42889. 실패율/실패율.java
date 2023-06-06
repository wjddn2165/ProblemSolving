import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] userCount = new int[N + 2];
        
        for(int i=0;i<stages.length;i++) {
            userCount[stages[i]]++;
        }
        
        int remainUser = stages.length;
        
        List<Stage> list = new ArrayList<>();
        
        for(int i=1;i<=N;i++) {
            double fail = (remainUser == 0) ? 0 : (double) userCount[i] / remainUser;
            remainUser -= userCount[i];
            
            list.add(new Stage(i, fail));
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
    
    Stage(int num, double fail) {
        this.num = num;
        this.fail = fail;
    }
    
    @Override
    public int compareTo(Stage o) {
        if(Double.compare(fail, o.fail) == 0) {
            return num - o.num;
        }
        
        return Double.compare(o.fail, fail);
    }
}