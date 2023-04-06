import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        long result = 0;

        if (2 * W <= S) {
            result += ((long) (X + Y)) * W;
        } else if (W <= S) {
            result += (long) Math.min(X, Y) * S;
            result += (long) Math.abs(X - Y) * W;
        } else {
            result += (long) Math.min(X, Y) * S;

            if (Math.abs(X - Y) % 2 == 0) {
                result += (long) Math.abs(X - Y) * S;
            } else {
                result += (long) (Math.abs(X - Y) - 1) * S;
                result += W;
            }
        }

        System.out.println(result);
    }
}