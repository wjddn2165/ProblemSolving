import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] crane = new int[N];
        int[] cnt = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            crane[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(crane);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int maxBoxSize = 0;
        for (int i = 0; i < M; i++) {
            int box = Integer.parseInt(st.nextToken());
            maxBoxSize = Math.max(box, maxBoxSize);
            for (int j = 0; j < N; j++) {
                if (box <= crane[j]) {
                    cnt[j]++;
                    break;
                }
            }
        }

        // 가장 무거운 무게를 옮기는 크레인의 최대 무게보다, 박스의 최대 무게가 더 큰 경우
        if (maxBoxSize > crane[N - 1]) {
            System.out.println(-1);
            return;
        }

        for (int i = (M + N - 1) / M; i < M; i++) {
            int temp = cnt[0];
            for (int j = 0; j < N - 1; j++) {
                if (temp > i) {
                    temp = cnt[j + 1] + temp - i;
                } else {
                    temp = cnt[j + 1];
                }
            }

            if (temp <= i) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(M);
    }
}