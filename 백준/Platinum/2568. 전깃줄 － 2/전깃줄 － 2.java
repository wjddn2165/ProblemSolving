import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int A = 0;
    static final int B = 1;
    static int N;
    static List<int[]> wires;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        wires = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            wires.add(new int[]{A, B});
        }

        wires.sort(new Sort());

        ArrayList<Integer> list = new ArrayList<>();
        int[] preIndex = new int[N];

        list.add(wires.get(0)[B]);

        for (int i = 1; i < wires.size(); i++) {
            int key = wires.get(i)[B];
            int size = list.size();

            if (list.get(size - 1) < key) {
                list.add(key);
                preIndex[i] = size;
            }

            int lo = 0;
            int hi = size - 1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (list.get(mid) < key) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            list.set(lo, key);
            preIndex[i] = lo;
        }

        sb.append(N - list.size()).append("\n");

        HashSet<Integer> remove = new HashSet<>();
        int temp = list.size() - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (preIndex[i] == temp) {
                remove.add(i);
                temp--;
                if (temp == -1) {
                    break;
                }
            }
        }

        for (int i = 0; i < wires.size(); i++) {
            if (remove.contains(i)) {
                continue;
            }
            sb.append(wires.get(i)[A]).append("\n");
        }
        System.out.print(sb);
    }

    static class Sort implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[A] - o2[A];
        }
    }
}