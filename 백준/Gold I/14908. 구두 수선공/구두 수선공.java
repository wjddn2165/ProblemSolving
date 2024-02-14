import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Work> works = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            works.add(new Work(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i + 1));
        }

        works.sort(null);

        StringBuilder answer = new StringBuilder();
        for (Work work : works) {
            answer.append(work.idx).append(" ");
        }

        System.out.println(answer);
    }
}

class Work implements Comparable<Work>{
    int t, s, idx;

    public Work(int t, int s, int idx) {
        this.t = t;
        this.s = s;
        this.idx = idx;
    }

    @Override
    public int compareTo(Work o) {
        return Double.compare((double) o.s / o.t, (double) s / t);
    }
}