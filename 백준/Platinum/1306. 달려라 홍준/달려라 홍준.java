import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder result = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Deque<int[]> queue = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());

        int windowSize = 2 * M - 1;

        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());

            while (!queue.isEmpty() && queue.peekLast()[0] < cur) {
                queue.removeLast();
            }

            queue.offerLast(new int[]{cur, i});

            if (queue.peek()[1] < i - windowSize + 1) {
                queue.removeFirst();
            }

            if (i >= windowSize - 1) {
                result.append(queue.peek()[0]).append(" ");
            }
        }

        System.out.print(result);
    }
}