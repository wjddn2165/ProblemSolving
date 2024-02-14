import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        StringTokenizer st3 = new StringTokenizer(br.readLine());

        List<Problem> problems = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            problems.add(new Problem(Integer.parseInt(st1.nextToken()), Integer.parseInt(st2.nextToken()), Integer.parseInt(st3.nextToken())));
        }

        problems.sort(null);

        // dp[i] = i라는 시간동안 얻을 수 있는 최대 점수
        long[] dp = new long[T + 1];

        for (int i = 0; i < N; i++) {
            Problem cur = problems.get(i);
            int m = cur.m;
            int p = cur.p;
            int r = cur.r;

            for (int j = T; j >= r; j--) {
                dp[j] = Math.max(dp[j], dp[j - r] + m - (long) j * p);
            }
        }

        long answer = 0;

        for (int i = 0; i <= T; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}

class Problem implements Comparable<Problem> {
    int m, p, r;

    public Problem(int m, int p, int r) {
        this.m = m;
        this.p = p;
        this.r = r;
    }

    @Override
    public int compareTo(Problem o) {
        return Double.compare((double) r / p, (double) o.r / o.p);
    }
}