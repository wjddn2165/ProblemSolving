import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static final String[] op = {"+", "-", " "};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            N = Integer.parseInt(br.readLine());
            List<String> result = new ArrayList<>();
            dfs(0, "1", result);
            result.sort(null);

            for (String s : result) {
                sb.append(s).append("\n");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int depth, String cur, List<String> result) {
        if (depth == N - 1) {
            String temp = cur.replaceAll(" ", "");

            String[] operand = temp.split("[+-]");
            String[] operator = temp.split("[0-9]+");

            int sum = Integer.parseInt(operand[0]);

            for (int i = 1; i < operator.length; i++) {
                if (operator[i].equals("+")) {
                    sum += Integer.parseInt(operand[i]);
                } else {
                    sum -= Integer.parseInt(operand[i]);
                }
            }

            if (sum == 0) {
                result.add(cur);
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            dfs(depth + 1, cur + op[i] + (depth + 2), result);
        }
    }
}