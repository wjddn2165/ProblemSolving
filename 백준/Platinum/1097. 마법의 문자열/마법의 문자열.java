import java.io.*;

class Main {

    static int N;
    static String[] words;
    static int K;
    static boolean[] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        K = Integer.parseInt(br.readLine());
        visited = new boolean[N];

        dfs(0, new StringBuilder());

        System.out.println(answer);
    }

    static boolean solution(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.append(s);

        int n = s.length();
        int[] pi = new int[n];
        int j = 0;

        for (int i = 1; i < n; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }

            if (s.charAt(i) == s.charAt(j)) {
                pi[i] = ++j;
            }
        }

        int count = 0;
        j = 0;

        for (int i = 0; i < sb.length() - 1; i++) {
            while (j > 0 && sb.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }

            if (sb.charAt(i) == s.charAt(j)) {
                j++;

                if (j == n) {
                    count++;
                    j = pi[j - 1];
                }
            }
        }

        return count == K;
    }

    static void dfs(int depth, StringBuilder cur) {
        if (depth == N) {
            if (solution(cur.toString())) {
                answer++;
            }

            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                int oldSize = cur.length();
                cur.append(words[i]);
                visited[i] = true;
                dfs(depth + 1, cur);
                visited[i] = false;
                cur.delete(oldSize, cur.length());
            }
        }
    }
}