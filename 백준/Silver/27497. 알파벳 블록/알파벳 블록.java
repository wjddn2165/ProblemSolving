import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Deque<Character> list = new ArrayDeque<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                list.addLast(st.nextToken().charAt(0));
                stack.push(1);
            } else if (command == 2) {
                list.addFirst(st.nextToken().charAt(0));
                stack.push(2);
            } else {
                if (list.size() == 0) {
                    continue;
                } else {
                    int prev = stack.pop();
                    if (prev == 1) {
                        list.removeLast();
                    } else if (prev == 2) {
                        list.removeFirst();
                    }
                }
            }
        }

        if (list.size() == 0) {
            System.out.println(0);
        } else {
            StringBuilder answer = new StringBuilder();
            for (Character character : list) {
                answer.append(character);
            }

            System.out.println(answer);
        }
    }
}