import java.util.*;

class Solution {
    
    List<List<Integer>> keys = new ArrayList<>();
    
    public int solution(String[][] relation) {
        dfs(relation, new ArrayList<>(), 0);
        
        int answer = keys.size();
        
        for(int i=0;i<keys.size();i++) {
            for(int j=0;j<keys.size();j++) {
                if(i!=j) {
                    if(keys.get(i).containsAll(keys.get(j))) {
                        answer--;
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
    
    void dfs(String[][] relation, List<Integer> columns, int depth) {
        if(isKey(relation, columns)) {
            keys.add(new ArrayList<>(columns));
            return;
        }
            
        for(int i=depth;i<relation[0].length;i++) {
            columns.add(i);
            dfs(relation, columns, i + 1);
            columns.remove(columns.size() - 1);
        }
    }
    
    boolean isKey(String[][] relation, List<Integer> columns) {
        
        // StringBuilder sb = new StringBuilder();
        // for(int i=0;i<columns.size();i++) {
        //     sb.append(columns.get(i)).append(" ");
        // }
        // System.out.println(sb);
        if(columns.size() == 0) return false;
        
        for(int i=0;i<relation.length - 1;i++) {
            for(int j=i+1;j<relation.length;j++) {
                boolean equal = true;
                
                for(int column : columns) {
                    equal = equal && relation[i][column].equals(relation[j][column]);
                }
                
                if(equal) return false;
            }
        }
        
        return true;
    }
}


