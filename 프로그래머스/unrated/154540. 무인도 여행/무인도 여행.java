import java.util.*;

class Solution {

    int bfs(int[][] board, boolean[][] visited, int r, int c) {

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(r, c));
        visited[r][c] = true;

        int row = board.length;
        int col = board[0].length;

        // 동서남북
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};

        int sum = 0;

        while(!queue.isEmpty()) {
            Point cur = queue.remove();
            sum += board[cur.r][cur.c] - '0';

            for(int i=0;i<4;i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if(nr >= 0 && nr < row && nc >= 0 && nc < col) {
                    if (board[nr][nc] != 'X' && !visited[nr][nc]) {
                        queue.offer(new Point(nr, nc));
                        visited[nr][nc] = true;
                    }
                    
                }
            }
        }

        return sum;
    }

    public int[] solution(String[] maps) {
        List<Integer> result = new ArrayList<>();

        int row = maps.length;
        int col = maps[0].length();

        int[][] board = new int[row][col];
        boolean[][] visited = new boolean[row][col];

        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                board[i][j] = maps[i].charAt(j);
            }
        }

        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                if(board[i][j] != 'X' && !visited[i][j]) {
                    result.add(bfs(board, visited, i, j));
                }
            }
        }

        if(result.size() == 0) {
            return new int[]{-1};
        }

        result.sort(null);

        int[] answer = new int[result.size()];

        for(int i=0;i<result.size();i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}

class Point {
    int r;
    int c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}