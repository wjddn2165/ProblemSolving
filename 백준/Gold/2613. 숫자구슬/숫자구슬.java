import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int[] arr;

    static List<Integer> result = new LinkedList<>();

    static int upperBound(int lo, int hi) {
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (isPossible(mid)) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    static boolean isPossible(int k) {
        int sum = arr[0];
        int count = 1;

        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];

            if (sum > k) {
                count ++;
                sum = arr[i];

                if (count > M) {
                    return false;
                }
            }
        }

        return true;
    }

    static void findCount(int k) {
        int sum = arr[0];
        int count = 1;

        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];

            if (sum > k) {
                result.add(count);

                count = 1;
                sum = arr[i];
                continue;
            }

            count++;
        }

        if (count != 0) {
            result.add(count);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        int sum = 0;
        int max = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }

        int findValue = upperBound(max, sum);
        findCount(findValue);

        while (result.size() != M) {
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i) > 1) {
                    Integer temp = result.remove(i);
                    result.add(i, temp - 1);
                    result.add(i, 1);
                    break;
                }
            }
        }

        sb.append(findValue).append("\n");

        for (Integer integer : result) {
            sb.append(integer).append(" ");
        }

        System.out.print(sb);
    }
}