import java.util.*;

class Solution {
   public int solution(String[] friends, String[] gifts) {
       int answer = 0;

       // rank는 선물 지수를 뜻함
       Map<String, Map<String, Integer>> map = new HashMap<>();
       Map<String, Integer> rank = new HashMap<>();

       for (int i = 0; i < friends.length; i++) {
           map.put(friends[i], new HashMap<>());
           rank.put(friends[i], 0);
       }

       for (int i = 0; i < gifts.length; i++) {
           String[] strs = gifts[i].split(" ");
           String from = strs[0];
           String to = strs[1];

           rank.put(from, rank.get(from) + 1);
           rank.put(to, rank.get(to) - 1);

           Map<String, Integer> subMap = map.get(from);
           subMap.put(to, subMap.getOrDefault(to, 0) + 1);
       }

       int[] score = new int[friends.length];

       for (int i = 0; i < friends.length - 1; i++) {
           for (int j = i + 1; j < friends.length; j++) {
               String from = friends[i];
               String to = friends[j];

               int fromCount = map.get(from).getOrDefault(to, 0);
               int toCount = map.get(to).getOrDefault(from, 0);

               if (fromCount > toCount) {
                   score[i]++;
               } else if (toCount > fromCount) {
                   score[j]++;
               } else {
                   int fromRank = rank.get(from);
                   int toRank = rank.get(to);
                   if (fromRank > toRank) {
                       score[i]++;
                   } else if (toRank > fromRank) {
                       score[j]++;
                   }
               }
           }
       }

       int max = 0;
       for (int i = 0; i<friends.length; i++) {
           max = Math.max(max,score[i]);
       }

       return max;
   }
}