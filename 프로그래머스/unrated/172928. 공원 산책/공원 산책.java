class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] answer = {};
        
        int row = park.length;
        int col = park[0].length();
        
        int[][] board = new int[row][col];
        
        int cr = 0;
        int cc = 0;
        
        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                board[i][j] = park[i].charAt(j);
                
                if(board[i][j] == 'S') {
                    cr = i;
                    cc = j;
                }
            }
        }
        
        // 북동남서 순서
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        outer: for(String route : routes) {
            int op = route.split(" ")[0].charAt(0);
            int n = Integer.parseInt(route.split(" ")[1]);
            
            int nr = cr;
            int nc = cc;
            int dir = 0;
           
            switch(op) {
                case 'N': 
                    dir = 0;
                    break;
                case 'E':
                    dir = 1;
                    break;
                case 'S':
                    dir = 2;
                    break;
                case 'W':
                    dir = 3;
            }
            
            for(int i=0;i<n;i++) {
                nr += dr[dir];
                nc += dc[dir];
                
                // 공원을 벗어나거나, 장애물을 만나면 다음 명령 수행
                if(nr >= row || nr < 0 || nc >= col || nc < 0 || board[nr][nc] == 'X') {
                continue outer;
                }
            }
            
            cr = nr;
            cc = nc;
        }
        
        return new int[]{cr, cc};
    }
}