import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Balloon> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new Balloon(i + 1, Integer.parseInt(st.nextToken())));
        }

        StringBuilder answer = new StringBuilder();

        int curIdx = 0;
        while (true) {
            Balloon cur = list.get(curIdx);
            answer.append(cur.idx).append(" ");
            list.remove(cur);

            int listSize = list.size();

            if (listSize == 1) {
                break;
            }

            int nextMove = 0;

            if (cur.value > 0) {
                nextMove = cur.value - 1;
            } else {
                nextMove = cur.value;
            }

            curIdx += nextMove;
            curIdx += listSize * 1000;
            curIdx %= listSize;
        }
        
        answer.append(list.get(0).idx);
        System.out.println(answer);
    }
}

class Balloon {
    int idx;
    int value;

    public Balloon(int idx, int value) {
        this.idx = idx;
        this.value = value;
    }
}