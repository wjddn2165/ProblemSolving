class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        // 1
        new_id = new_id.toLowerCase();
        // System.out.println(new_id);
        
        // 2
        new_id = new_id.replaceAll("[^a-z0-9-_\\.]", "");
        // System.out.println(new_id);
        
        // 3
        new_id = new_id.replaceAll("[\\.]+", ".");
        // System.out.println(new_id);
        
        // 4
        new_id = new_id.replaceAll("^\\.|\\.$", "");
        // System.out.println(new_id);
        
        // 5
        if(new_id.length() == 0) {
            new_id = "a";
        }
        // System.out.println(new_id);
        
        // 6
        if(new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
        }
        // System.out.println(new_id);
        
        new_id = new_id.replaceAll("\\.$", "");
        
        //7
        if(new_id.length() <= 2) {
            while(new_id.length() < 3) {
                new_id += new_id.charAt(new_id.length() - 1);
            }
        }
        // System.out.println(new_id);
        
        return new_id;
    }   
}