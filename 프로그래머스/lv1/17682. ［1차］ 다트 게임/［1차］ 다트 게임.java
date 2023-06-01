import java.util.regex.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;

        Pattern pattern = Pattern.compile("(\\d+)([SDT])([*#])?");
        Matcher matcher = pattern.matcher(dartResult);
        
        int[] scores = new int[3];
        
        for(int i=0;i<3;i++) {
            matcher.find();
            
            int score = Integer.parseInt(matcher.group(1));
            String bonus = matcher.group(2);
            String option = null;
            
            if(matcher.group().length() == 3)
                option = matcher.group(3);
            
            if(bonus.equals("S")) {
                scores[i] = score;
            } else if(bonus.equals("D")) {
                scores[i] = score * score;
            } else {
                scores[i] = score * score * score;
            }
            
            if(option == null) continue;
            
            if(option.equals("*")) {
                scores[i] *= 2;
                if(i != 0) scores[i - 1] *= 2;
            } else {
                scores[i] *= -1;
            }
        }
        
        return scores[0] + scores[1] + scores[2];
    }
}