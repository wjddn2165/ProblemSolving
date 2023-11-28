import java.io.*;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // (100~1~|01)~
        String regExp = "^((100+1+)|(01))+$";
        if (Pattern.matches(regExp, str)) {
            System.out.println("SUBMARINE");
        } else {
            System.out.println("NOISE");
        }
    }
}