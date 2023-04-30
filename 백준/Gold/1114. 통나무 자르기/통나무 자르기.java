import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int L;
    static int K;
    static int C;
    static List<Integer> list;
    static List<Integer> index;
    static int upperBound(int maxDist) {
        int lo = maxDist;
        int hi = L;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (isPossible(mid)) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    static boolean isPossible(int k) {
        int left = 0;
        int count = 0;

        index = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {
            int right = list.get(i);

            if (right - left > k) {
                count++;
                left = list.get(i - 1);

                if (count > C) {
                    return false;
                }
            }
        }

        return L - left <= k;
    }

    static int findIndex(int k) {
        int right = L;
        int count  = 0;

        for (int i = list.size() - 1; i >= 0; i--) {
            int left = list.get(i);

            if (right - left > k) {
                count++;
                right = list.get(i + 1);
            }
        }

        if (count == C) {
            return right;
        } else {
            return list.get(1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < K; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        list = new ArrayList<>(set);

        list.add(0);
        list.add(L);

        list.sort(null);

        int maxDist = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) - list.get(i - 1) > maxDist) {
                maxDist = list.get(i) - list.get(i - 1);
            }
        }

        int maxPiece = upperBound(maxDist);
        int minIndex = findIndex(maxPiece);

        System.out.println(maxPiece + " " + minIndex);
    }
}