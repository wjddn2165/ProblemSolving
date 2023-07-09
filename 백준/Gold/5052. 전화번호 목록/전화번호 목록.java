import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        outer: while (t--> 0) {
            List<String> list = new ArrayList<>();

            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                list.add(br.readLine());
            }

            list.sort(Comparator.reverseOrder());

            for (int i = 0; i < n - 1; i++) {
                if (list.get(i).startsWith(list.get(i + 1))) {
                    sb.append("NO").append("\n");
                    continue outer;
                }
            }

            sb.append("YES").append("\n");
        }

        System.out.println(sb);
    }
}