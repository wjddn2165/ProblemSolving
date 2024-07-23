import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int src = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        int add = 0;
        int count = 0;

        while (src != dest) {
            count++;
            long sum = sum(add + 1);
            int diff = dest - src;
            if (sum > diff) {
                sum = sum(add);
                if (sum > diff) {
                    add--;
                }
                src += add;
            } else if (sum < diff) {
                add++;
                src += add;
            } else {
                src += add + 1;
            }
        }

        System.out.println(count);
    }

    public static long sum(int n) {
        return (long) n * (n + 1) / 2;
    }
}