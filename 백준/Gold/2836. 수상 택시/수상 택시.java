import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Pair> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a > b) list.add(new Pair(b, a));
        }

        list.sort((a, b) -> a.l - b.l);

        int left = -1;
        int right = -1;
        long sum = 0;

        for (int i = 0; i < list.size(); i++) {
            Pair next = list.get(i);

            if (right < next.l) {
                sum += right - left;
                left = next.l;
                right = next.r;
            } else {
                right = Math.max(right, next.r);
            }
        }

        sum += right - left;

        System.out.println(sum * 2 + m);
    }
}

class Pair {
    int l;
    int r;

    Pair(int l, int r) {
        this.l = l;
        this.r = r;
    }
}