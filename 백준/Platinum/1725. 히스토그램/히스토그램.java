import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] height = new int[N];

        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(rec(height, 0, N - 1));
    }

    static int rec(int[] height, int left, int right) {
        if (left == right) {
            return height[left];
        }

        int mid = (left + right) >> 1;
        int leftMaxValue = rec(height, left, mid);
        int rightMaxValue = rec(height, mid + 1, right);

        // merge
        int max = Math.max(leftMaxValue, rightMaxValue);

        int l = mid;
        int r = mid + 1;

        int min = Math.min(height[l], height[r]);

        while (true) {
            max = Math.max(max, min * (r - l + 1));

            if (l == left && r == right) {
                break;
            }

            if (l == left) {
                min = Math.min(min, height[++r]);
            } else if (r == right) {
                min = Math.min(min, height[--l]);
            } else {
                if (height[l - 1] < height[r + 1]) {
                    min = Math.min(min, height[++r]);
                } else {
                    min = Math.min(min, height[--l]);
                }
            }
        }

        return max;
    }
}