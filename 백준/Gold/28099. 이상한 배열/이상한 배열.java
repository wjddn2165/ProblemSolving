import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        outer: while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            TreeSet<Integer> set = new TreeSet<>();

            int[] arr = new int[N];
            int[][] index = new int[N + 1][2];

            for (int i = 1; i <= N; i++) {
                index[i][0] = -1;
                index[i][1] = -1;
            }

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());

                if (index[arr[i]][0] != -1) {
                    index[arr[i]][1] = i;
                } else {
                    index[arr[i]][0] = i;
                }
            }

            for (int i = 0; i < N; i++) {
                // 현재 보고있는 값이, 현재 set 에 있는 최솟값보다 크면 바로 No 출력
                if (!set.isEmpty() && set.first() < arr[i]) {
                    sb.append("No").append("\n");
                    continue outer;
                }
                
                // 최소 2개 이상 존재하는 수에 대해서만 set 에 넣음
                if(index[arr[i]][1] != -1) {
                    if (index[arr[i]][0] == i) {
                        set.add(arr[i]);
                    }

                    if (index[arr[i]][1] == i) {
                        set.remove(arr[i]);
                    }
                }
            }

            sb.append("Yes").append("\n");
        }

        System.out.print(sb);
    }
}