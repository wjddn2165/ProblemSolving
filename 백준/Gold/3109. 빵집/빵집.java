import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {-1, 0, 1};
    static int[] dc = {1, 1, 1};
    static boolean[][] visited;
    static int R;
    static int C;
    static int[][] map;
    static boolean find = false;
    static int result = 0;

    static void dfs(int r, int c) {
        if (c == C - 1) {
            result++;
            find = true;
            return;
        }

        for (int i = 0; i < 3; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nr < R) {
                if (!visited[nr][nc] && map[nr][nc] == '.') {
                    visited[nr][nc] = true;
                    dfs(nr, nc);

                    // 끝에 도달했으면 모든 dfs 함수 stack 종료
                    if (find) {
                        return;
                    }

                    // 불필요 함. 원래 백트래킹으로 돌아올 때 해당 경로를 다시 방문할 수도 있기 때문에 다시 false 처리를 해주는 것인데, 다시 방문할 필요가 없음.
//                    visited[nr][nc] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];


        for (int i = 0; i < R; i++) {
            String str = br.readLine();

            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            find = false;
            dfs(i, 0);
        }

        System.out.println(result);
    }
}