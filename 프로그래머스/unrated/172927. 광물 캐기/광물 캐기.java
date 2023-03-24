class Solution {
    
    int min = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        int[][] waste = {{1, 1, 1},
                         {5, 1, 1},
                         {25, 5, 1}};
        
        dfs(picks, minerals, 0, 0, minerals.length, waste);
        
        return min;
    }
    
    void dfs(int[] picks, String[] minerals, int fatigue, int index, int size, int[][] waste) {
        
        int pickCount = picks[0] + picks[1] + picks[2];
        
        if(index == size || pickCount == 0) {
            min = Math.min(min, fatigue);
            return;
        }
        
        
        for(int i=0;i<picks.length;i++) {
            
            // 백트래킹 시 index변수를 유지하기 위해 copyIndex 사용
            int copyIndex = index;

            if(picks[i] > 0) {
                
                picks[i]--;
                
                int use = 0;
                int sum = 0;
                
                while(use < 5 && copyIndex != size) {
                    use++;
                    
                    int mineral = parseIndex(minerals[copyIndex++]);
                    sum += waste[i][mineral];
                }
                
                dfs(picks, minerals, fatigue + sum, copyIndex, size, waste);
                picks[i]++;
            }
        }
    }
    
    int parseIndex(String mineral) {
        int index = 0;
        
        if(mineral.equals("iron")) {
            index = 1;
        }
        
        if(mineral.equals("stone")) {
            index = 2;
        }
        
        return index;
    }
}
