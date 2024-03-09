import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int ODD = 0;
    static final int EVEN = 1;
    static int[] nums;
    static int[][] segmentTree;

    static int[] merge(int[] left, int[] right) {
        return new int[]{left[ODD] + right[ODD], left[EVEN] + right[EVEN]};
    }

    static int[] build(int root, int left, int right) {
        if (left == right) {
            if (nums[left] % 2 == 0) {
                return segmentTree[root] = new int[]{0, 1};
            }
            return segmentTree[root] = new int[]{1, 0};
        }

        int mid = (left + right) / 2;
        int[] leftChild = build(root * 2, left, mid);
        int[] rightChild = build(root * 2 + 1, mid + 1, right);
        return segmentTree[root] = merge(leftChild, rightChild);
    }

    static int[] update(int root, int left, int right, int index, int value) {
        if (left == index && right == index) {
            if (value % 2 == 0) {
                return segmentTree[root] = new int[]{0, 1};
            }
            return segmentTree[root] = new int[]{1, 0};
        }

        if (index < left || right < index) {
            return segmentTree[root];
        }

        int mid = (left + right) / 2;
        int[] leftChild = update(root * 2, left, mid, index, value);
        int[] rightChild = update(root * 2 + 1, mid + 1, right, index, value);
        return segmentTree[root] = merge(leftChild, rightChild);
    }

    static int[] query(int root, int left, int right, int start, int end) {
        if (start <= left && right <= end) {
            return segmentTree[root];
        }

        if (right < start || end < left) {
            return new int[]{0, 0};
        }

        int mid = (left + right) / 2;
        int[] leftChild = query(root * 2, left, mid, start, end);
        int[] rightChild = query(root * 2 + 1, mid + 1, right, start, end);
        return merge(leftChild, rightChild);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        nums = new int[N];
        segmentTree = new int[4 * N][];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        build(1, 0, N - 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 1) {
                update(1, 0, N - 1, a - 1, b);
            }

            int[] query = query(1, 0, N - 1, a - 1, b - 1);

            if (op == 2) {
                sb.append(query[1]).append("\n");
            }
            if (op == 3) {
                sb.append(query[0]).append("\n");
            }
        }
        System.out.print(sb);
    }
}