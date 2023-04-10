import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[] visited = new boolean[10];
    static List<String> result = new ArrayList<>();
    static int[] op;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        op = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            op[i] = st.nextToken().charAt(0);
        }

        dfs("");

        System.out.println(result.get(result.size() - 1));
        System.out.println(result.get(0));

    }

    static void dfs(String cur) {
        if (cur.length() == n + 1) {
            result.add(cur);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (!visited[i]) {
                if (cur.length() == 0) {
                    visited[i] = true;
                    dfs(cur + i);
                    visited[i] = false;
                } else {
                    if (op[cur.length() - 1] == '<') {
                        if (cur.charAt(cur.length() - 1) - '0' < i) {
                            visited[i] = true;
                            dfs(cur + i);
                            visited[i] = false;
                        }
                    } else {
                        if (cur.charAt(cur.length() - 1) - '0' > i) {
                            visited[i] = true;
                            dfs(cur + i);
                            visited[i] = false;
                        }
                    }
                }

            }
        }
    }
}