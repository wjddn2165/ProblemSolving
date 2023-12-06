import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(dfs(input));
    }

    static int dfs(String str) {
        if (str.isEmpty()) {
            return 1;
        }

        if (str.length() % 2 != 0) {
            System.out.println(0);
            System.exit(0);
        }

        int left = 0;
        int right = 0;
        int startIdx = 0;
        int result = 0;

        for (int i = 0; i < str.length(); i++) {
            // 여는 괄호라면
            if (str.charAt(i) == '(' || str.charAt(i) == '[') {
                left ++;
            } else if (str.charAt(i) == ')' || str.charAt(i) == ']') {
                right++;
            } else {
                System.out.println(0);
                System.exit(0);
            }

            if (left == right) {
                int start = str.charAt(startIdx);
                int end = str.charAt(i);
                // 올바론 괄호열이 아닌 경우
                if((start != '(' && start != '[') ||
                        (start == '(' && end == ']') ||
                        (start == '[' && end == ')')) {
                    System.out.println(0);
                    System.exit(0);
                }

                if (end == ')') {
                    result += 2 * dfs(str.substring(startIdx + 1, i));
                } else {
                    result += 3 * dfs(str.substring(startIdx + 1, i));
                }

                startIdx = i + 1;
            }
        }

        if (left != right) {
            System.out.println(0);
            System.exit(0);
        }

        return result;
    }
}