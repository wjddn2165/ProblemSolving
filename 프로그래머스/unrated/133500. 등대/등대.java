import java.util.*;

class Solution {
    
    List<List<Integer>> graph;
    boolean[] visited;
    int count = 0;
    
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        
        graph = new ArrayList<>();
        
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] edge : lighthouse) {
            int from = edge[0];
            int to = edge[1];
            
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        
        visited = new boolean[n + 1];
        
        // 트리에서는 어떤 노드를 루트로 잡아도 상관없기 때문에 1을 루트로 설정하여 탐색
        dfs(1);
        
        return count;
    }
    
    // true는 등대를 켜야하는 노드, false는 등대가 꺼진 노드
    boolean dfs(int cur) {
        // 양방향 그래프를 구성했기 때문에 visited로 체크
        visited[cur] = true;
        
        // 자식이 모두 true일 때만 부모가 false이므로 이를 검증하기 위한 flag 변수
        boolean flag = true;
        
        // 만약 leaf 노드라면 무조건 false를 반환해야 하므로 이를 검증하기 위한 isLeaf 변수
        boolean isLeaf = true;
        
        for(int next : graph.get(cur)) {
            if(!visited[next]) {
                // 다음으로 갈 수 있는 노드가 하나라도 있다면 리프노드가 아님
                isLeaf = false;
                flag = dfs(next) && flag;
            }
        }
        
        // 리프노드이거나, 자식 노드가 전부 true이면 flag가 true이므로 부모노드는 false
        if(isLeaf || flag) return false;
        
        // 자식 노드 중 하나라도 false가 있는 경우
        count++;
        return true;
    }
}