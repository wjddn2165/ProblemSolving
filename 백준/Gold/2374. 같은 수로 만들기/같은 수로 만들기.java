import java.io.*;
import java.util.*;

class Main {

    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        int maxValue = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            maxValue = Math.max(maxValue, arr[i]);
        }

        System.out.println(dfs(arr, 0, N - 1, maxValue));
    }

    static long dfs(int[] arr, int left, int right, int prevMax) {
        if (left > right) {
            return 0;
        }

        if (left == right) {
            return prevMax - arr[left];
        }

        int max = 0;
        int maxIdx = 0;

        for (int i = left; i <= right; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxIdx = i;
            }
        }

        long sum = dfs(arr, left, maxIdx - 1, max) + dfs(arr, maxIdx + 1, right, max);

        return sum + (long) (prevMax - max);
    }
}