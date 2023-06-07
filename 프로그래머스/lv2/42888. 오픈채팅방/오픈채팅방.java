import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        // 유저 id, 유저 닉네임
        Map<String, String> map = new HashMap<>();
        
        for(int i=0;i<record.length;i++) {
            String[] s = record[i].split("\\s");
            String op = s[0];
            String id = s[1];
            
            String name = null;
            
            if(s.length == 3) {
                name = s[2];
            }
            
            if(op.equals("Change")) {
                User.map.put(id, name);
            } else if(op.equals("Enter")) {                
                if(User.isChange(id, name)) {
                    map.put(id, name);
                }
                
                User.list.add(new User(id, name, op));
            } else {
                User.list.add(new User(id, User.map.get(id), op));
            }
        }
        
        // 변경은 한번만 하면 됨
        User.change();
        
        String[] answer = new String[User.list.size()];
        
        for(int i=0;i<answer.length;i++) {
            answer[i] = User.list.get(i).toString();
        }
        
        return answer;
    }
}

class User {
    static Map<String, String> map = new HashMap<>();
    static List<User> list = new ArrayList<>();
    
    String id;
    String name;
    String op;
    
    User(String id, String name, String op) {
        this.id = id;
        this.name = name;
        this.op = op;
        
        map.put(id, name);
    }
    
    static void change() {
        for(User user : list) {
            if(isChange(user.id, user.name)) {
                user.name = map.get(user.id);
            }
        }
    }
    
    static boolean isChange(String id, String name) {
        if(map.get(id) == null) return false;
        return !map.get(id).equals(name);
    }
    
    @Override
    public String toString() {
        if(op.equals("Enter")) {
            return name + "님이 들어왔습니다.";
        } else if(op.equals("Leave")) {
            return name + "님이 나갔습니다.";
        }
        
        return null;
    }
}