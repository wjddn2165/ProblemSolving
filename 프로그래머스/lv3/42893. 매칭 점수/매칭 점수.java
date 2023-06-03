import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String word, String[] pages) {
        // map에 모든 page 초기화
        for(int i=0;i<pages.length;i++) {
            Page page = new Page(pages[i], i);
            page.setUrl()
                .setOuterUrl()
                .findWord(word);
            Page.map.put(page.url, page);
        }
        
        // 점수 계산
        for(Page page : Page.map.values()) {
            page.calcLinkScore();
            
            System.out.println(page.totalScore);
            System.out.println("url : " + page.url);
            for(int i=0;i<page.outerUrl.size();i++) {
                System.out.println(page.outerUrl.get(i));
            }
        }
        
        // 최종점수 계산
        for(Page page : Page.map.values()) {
            page.calcTotalScore();
        }
        
        return Page.findIdx();
    }
}

class Page {
    static Map<String, Page> map = new HashMap<>();
    String html, url;
    int idx;
    List<String> outerUrl;
    double totalScore;
    int score;
    double linkScore;
    int degree;
    
    Page(String html, int idx) {
        this.html = html;
        this.idx = idx;
        outerUrl = new ArrayList<>();
        totalScore = 0;
        score = 0;
        linkScore = 0;
        degree = 0;
    }
    
    public Page setUrl() {
        Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(.*?)\"");
        Matcher matcher = pattern.matcher(html);
        matcher.find();
        url = matcher.group(1);
        return this;
    }
    
    public Page findWord(String word) {
        String[] words = html.toLowerCase().split("[^a-z]");
        
        for(int i=0;i<words.length;i++) {
            if(words[i].equals(word.toLowerCase())) {
                score++;
            }
        }
        
        return this;
    }
    
    public Page setOuterUrl() {
        Pattern pattern = Pattern.compile("<a href=\"https://(.*?)\"");
        Matcher matcher = pattern.matcher(html);
        
        while(matcher.find()) {
            outerUrl.add(matcher.group(1));
            degree++;
        }
        
        return this;
    }
    
    public Page calcLinkScore() {
        for(int i=0;i<outerUrl.size();i++) {
            Page outerPage = Page.map.get(outerUrl.get(i));
            // 왜 null pointer 예외가 터지지?
            if(outerPage == null) continue;
            outerPage.linkScore += (double)score / degree;
        }
        
        return this;
    }
    
    public Page calcTotalScore() {
        totalScore = score + linkScore;
        return this;
    }
    
    public static int findIdx() {
        int maxIdx = 0;
        double maxScore = 0.0;
        
        for(Page page : Page.map.values()) {
            if(maxScore < page.totalScore) {
                maxScore = page.totalScore;
                maxIdx = page.idx;
            }
        }
        
        return maxIdx;
    }
}