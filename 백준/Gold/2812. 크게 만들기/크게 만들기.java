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

        char[] result = new char[N - K];

        String input = br.readLine();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            char next = input.charAt(i);

            while (!stack.isEmpty() && stack.peek() < next && K-- > 0) {
                stack.pop();
            }

            stack.push(next);
        }

        for (int i = 0; i < result.length; i++) {
            result[i] = stack.get(i);
        }

        System.out.println(result);
    }
}