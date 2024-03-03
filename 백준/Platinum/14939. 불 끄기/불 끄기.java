import java.io.*;

class Main {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[10][10];
        for (int i = 0; i < 10; i++) {
            String str = br.readLine();
            for (int j = 0; j < 10; j++) {
                char ch = str.charAt(j);
                if (ch == 'O') {
                    board[i][j] = 1;
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int bit = 0; bit < (1 << 10); bit++) {
            int count = 0;

            int[][] newBoard = new int[10][10];
            for (int i = 0; i < 10; i++) {
                System.arraycopy(board[i], 0, newBoard[i], 0, 10);
            }

            for (int i = 0; i < 10; i++) {
                if ((bit & (1 << i)) > 0) {
                    count++;
                    toggle(newBoard, 0, i);
                }
            }

            for (int i = 1; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (newBoard[i - 1][j] == 1) {
                        count ++;
                        toggle(newBoard, i, j);
                    }
                }
            }

            if (check(newBoard)) {
                answer = Math.min(answer, count);
            }
        }
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static void toggle(int[][] board, int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= 10 || nc < 0 || nc >= 10) {
                continue;
            }

            board[nr][nc] ^= 1;
        }

        board[r][c] ^= 1;
    }

    static boolean check(int[][] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 1) {
                    return false;
                }
            }
        }

        return true;
    }
}