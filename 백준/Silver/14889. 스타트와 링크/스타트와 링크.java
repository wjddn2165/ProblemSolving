import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] score;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        score = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, new ArrayList<>());

        System.out.println(min);
    }

    static void dfs(int start, List<Integer> list) {
        if (list.size() == N / 2) {
            List<Integer> remain = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                if (!list.contains(i)) {
                    remain.add(i);
                }
            }

            min = Math.min(min, Math.abs(calc(list) - calc(remain)));
            return;
        }

        for (int i = start; i < N; i++) {
            list.add(i);
            dfs(i + 1, list);
            list.remove(list.size() - 1);
        }

    }

    static int calc(List<Integer> list) {
        int sum = 0;

        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2 && j != i; j++) {
                sum += (score[list.get(i)][list.get(j)] + score[list.get(j)][list.get(i)]);
            }
        }

        return sum;
    }
}