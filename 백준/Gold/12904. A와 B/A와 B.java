import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    // StringBuilder 클래스는 equal 메서드가 주소를 비교하기 때문에, 비교하는 메서드 구현
    static boolean isEqual(StringBuilder src, StringBuilder dest) {

        if (src.length() != dest.length()) {
            return false;
        }

        int size = src.length();

        for (int i = 0; i < size; i++) {
            if (src.charAt(i) != dest.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder src = new StringBuilder(br.readLine());
        StringBuilder dest = new StringBuilder(br.readLine());

        while (!isEqual(src, dest)) {
            // src 와 dest 가 다른데, 두 문자열의 길이가 같다는 건 절대 같게 만들 수 없다는 의미
            if (src.length() == dest.length()) {
                System.out.println(0);
                return;
            }

            int lastChar = dest.charAt(dest.length() - 1);

            // 일단 맨 뒤 문자 삭제
            dest.deleteCharAt(dest.length() - 1);

            // 만약 맨 뒤 문자가 'B' 였다면, 뒤집음
            if (lastChar == 'B') {
                dest.reverse();
            }
        }

        System.out.println(1);
    }
}