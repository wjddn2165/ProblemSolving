import java.io.*;
import java.util.*;

class Main {

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};
    static int N;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        boolean[][] board = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (st.nextToken().equals("1")) {
                    board[i][j] = true;
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int bit = 0; bit < (1 << N); bit++) {
            boolean[][] newBoard = new boolean[N][N];
            count = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j]) {
                        newBoard[i][j] = true;
                    }
                }
            }

            flipTop(newBoard, bit);

            for (int i = 1; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (newBoard[i - 1][j]) {
                        flip(newBoard, i, j);
                        count++;
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

    static void flipTop(boolean[][] board, int bit) {
        for (int i = 0; i < N; i++) {
            if ((bit & (1 << i)) > 0) {
                flip(board, 0, i);
                count++;
            }
        }
    }

    static void flip(boolean[][] board, int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                continue;
            }

            board[nr][nc] = !board[nr][nc];
        }

        board[r][c] = !board[r][c];
    }

    static boolean check(boolean[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}