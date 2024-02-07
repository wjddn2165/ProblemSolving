import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String next = st.nextToken();

            list.add(next);
        }

        list.sort(new BigNumberSort());

        StringBuilder answer = new StringBuilder();
        for (String s : list) {
            answer.append(s);
        }

        if (answer.charAt(0) == '0') {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}

class BigNumberSort implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return (o2 + o1).compareTo(o1 + o2);
    }

    public BigNumberSort() {
    }
}