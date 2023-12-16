import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(findLength(s));
    }

    static int findLength(String s) {
        if (s.length() == 0) {
            return 0;
        }

        int length = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && s.charAt(i + 1) == '(') {
                int left = 1;
                int right = 0;
                for (int j = i + 2; j < s.length(); j++) {
                    if (s.charAt(j) == '(') {
                        left ++;
                    } else if (s.charAt(j) == ')') {
                        right ++;
                    }

                    if(left == right) {
                        length += (s.charAt(i) - '0') * findLength(s.substring(i + 2, j));
                        i = j;
                        break;
                    }
                }
            } else {
                length++;
            }
        }

        return length;
    }
}