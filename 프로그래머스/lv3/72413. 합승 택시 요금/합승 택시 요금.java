class Solution {
    
    static final int INF = 99999999;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        int[][] dist = new int[n + 1][n + 1];
        
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(i != j) dist[i][j] = INF;
            }
        }
        
        for(int[] edge : fares) {
            int src = edge[0];
            int dest = edge[1];
            int weight = edge[2];
            
            dist[src][dest] = dist[dest][src] = weight;
        }
        
        for(int k=1;k<=n;k++) {
            for(int i=1;i<=n;i++) {
                for(int j=1;j<=n;j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        
        for(int i=1;i<=n;i++) {
            answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
        }
        
        return answer;
    }
}