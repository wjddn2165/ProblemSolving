import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] crane = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            crane[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(crane);
        int M = Integer.parseInt(br.readLine());
        LinkedList<Integer> boxes = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }
        boxes.sort(null);

        // 가장 무거운 무게를 옮기는 크레인의 최대 무게보다, 박스의 최대 무게가 더 큰 경우
        if (crane[N - 1] < boxes.getLast()) {
            System.out.println(-1);
            return;
        }

        int count = 0;
        while (!boxes.isEmpty()) {
            count++;
            for (int i = N - 1; i >= 0; i--) {
                int maxBoxIdx = lowerBound(boxes, crane[i]);
                if (maxBoxIdx == -1) {
                    break;
                }
                boxes.remove(maxBoxIdx);
            }
        }

        System.out.println(count);
    }

    static int lowerBound(LinkedList<Integer> boxes, int k) {
        int lo = 0;
        int hi = boxes.size() - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (boxes.get(mid) > k) {
                hi = mid - 1;
            } else if(boxes.get(mid) == k) {
                return mid;
            } else {
                lo = mid + 1;
            }
        }
        return hi;
    }
}