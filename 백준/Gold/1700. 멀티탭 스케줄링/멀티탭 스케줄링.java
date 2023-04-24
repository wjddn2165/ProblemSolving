import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[K];
        Set<Integer> set = new HashSet<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])) {
                continue;
            }

            if (set.size() < N) {
                set.add(arr[i]);
                continue;
            }

            Set<Integer> secondarySet = new HashSet<>(set);

            for (int j = i + 1; j < arr.length; j++) {
                if (secondarySet.size() == 1) {
                    break;
                }
                
                secondarySet.remove(arr[j]);
            }

            set.remove(secondarySet.iterator().next());
            set.add(arr[i]);
            count++;
        }

        System.out.println(count);
    }
}