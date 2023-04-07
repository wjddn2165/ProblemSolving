import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Long> list = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            list.add(Long.parseLong(st.nextToken()));
        }

        list.sort(null);

        Deque<Long> queue = new LinkedList<>(list);

        int count = 0;

        while (queue.size() != 1) {
            long minChain = queue.removeFirst();

            count++;

            if (minChain > 1) {

                // 가장 작은 체인에서 체인을 한 개 분리
                minChain--;
                queue.offerFirst(minChain);

                // 가장 큰 체인 두개를 합침
                long last = queue.removeLast() + queue.removeLast();
                queue.offerLast(last + 1);
            } else {
                // 가장 작은 체인이 1개 짜리 체인인데, 남은 체인이 하나만 있다면 바로 종료
                if (queue.size() == 1) {
                    System.out.println(count);
                    return;
                }

                // 가장 큰 체인 두개를 합침
                long last = queue.removeLast() + queue.removeLast();
                queue.offerLast(last + 1);
            }
        }

        System.out.println(count);
    }
}