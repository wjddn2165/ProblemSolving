import java.io.*;
import java.util.*;

class Main {

    static List<Info> infoList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int minDeadLine = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            infoList.add(new Info(d, t));

            minDeadLine = Math.min(minDeadLine, t);
        }

        infoList.sort(null);

        int lo = 0;
        int hi = minDeadLine;

        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (isPossible(mid)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        System.out.println(hi);
    }

    static boolean isPossible(int k) {
        int cur = k;

        for (Info info : infoList) {
            cur += info.d;
            if (cur > info.t) {
                return false;
            }
        }

        return true;
    }
}

class Info implements Comparable<Info> {
    int d;
    int t;

    public Info(int d, int t) {
        this.d = d;
        this.t = t;
    }

    @Override
    public int compareTo(Info o) {
        return t - o.t;
    }
}