import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Time> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Time(a, b));
        }

        list.sort(null);

        long sum = 0;
        long cur = 0;

        for (int i = 0; i < n; i++) {
            Time time = list.get(i);
            sum += (cur * time.a + time.b) % 40000;
            cur = sum;
        }

        System.out.println(cur % 40000);
    }
}

class Time implements Comparable<Time> {
    int a, b;
    Time(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Time o) {
        if (a == 0 || o.a == 0) {
            return o.a - a;
        }

        return o.a * b - a * o.b;
    }
}