import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] segmentTree;
    static int[] nums;

    static int merge(int left, int right) {
        return left * right;
    }

    // root : 현재 세그먼트 트리의 인덱스
    // left, right : 현재 세그먼트가 커버하는 범위
    static int init(int root, int left, int right) {
        if (left == right) {
            return segmentTree[root] = nums[left];
        }

        int mid = (left + right) / 2;
        int leftChild = init(root * 2, left, mid);
        int rightChild = init(root * 2 + 1, mid + 1, right);
        return segmentTree[root] = merge(leftChild, rightChild);
    }

    static int query(int root, int left, int right, int start, int end) {
        if (right < start || left > end) {
            return 1;
        }

        if (start <= left && right <= end) {
            return segmentTree[root];
        }

        int mid = (left + right) / 2;
        int leftChild = query(2 * root, left, mid, start, end);
        int rightChild = query(2 * root + 1, mid + 1, right, start, end);
        return merge(leftChild, rightChild);
    }

    static int update(int root, int left, int right, int index, int value) {
        if (index < left || index > right) {
            return segmentTree[root];
        }

        if (left == index && right == index) {
            return segmentTree[root] = value;
        }

        int mid = (left + right) / 2;
        int leftChild = update(root * 2, left, mid, index, value);
        int rightChild = update(root * 2 + 1, mid + 1, right, index, value);
        return segmentTree[root] = merge(leftChild, rightChild);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = "";
        while ((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            nums = new int[N];
            segmentTree = new int[4 * N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.compare(Integer.parseInt(st.nextToken()), 0);
            }

            init(1, 0, N - 1);

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                String op = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (op.equals("C")) {
                    b = Integer.compare(b, 0);
                    update(1, 0, N - 1, a - 1, b);
                }

                if (op.equals("P")) {
                    int result = query(1, 0, N - 1, a - 1, b - 1);

                    if (result == 1) {
                        sb.append('+');
                    }
                    if (result == -1) {
                        sb.append('-');
                    }
                    if (result == 0) {
                        sb.append(0);
                    }
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}