import java.util.*;

class Solution {
    
    List<Integer> keys = new ArrayList<>();
    
    public int solution(String[][] relation) {
        int row = relation.length;
        int col = relation[0].length;
        
        int answer = 0;
        
        for(int i=1;i<(1 << col);i++) {
            if(isKey(relation, i) && isMin(i)) {
                keys.add(i);
                // System.out.println(Integer.toString(i, 2));
            }
        }
        
        return keys.size();
    }
    
    boolean isMin(int key) {
        for(int i=0;i<keys.size();i++) {
            if((keys.get(i) & key) == keys.get(i)) return false;
        }
        return true;
    }
    
    boolean isKey(String[][] relation, int key) {
        int row = relation.length;
        int col = relation[0].length;
        
        for(int i=0;i<row;i++) {
            for(int j=i+1;j<row;j++) {
                boolean equal = true;
                
                for(int k=0;k<col;k++) {
                    
                    if((key & (1 << k)) > 0) {
                        equal = equal && relation[i][k].equals(relation[j][k]);
                    }
                }
                
                if(equal) return false;
            }
        }
        
        return true;
    }
}


