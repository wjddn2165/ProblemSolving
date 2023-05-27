import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int order = 0;
        
        Queue<Node> queue = new LinkedList<>();
        int[] count = new int[10];

        
        for(int i=0;i<priorities.length;i++) {
            queue.offer(new Node(priorities[i], i));
            count[priorities[i]]++;
        }
        
        
        outer: while(true) {
            Node cur = queue.remove();
            
            // 큐 안에 더 높은 우선순위를 가진 프로세스가 있으면 다시 큐 뒤에 삽입
            for(int i=cur.priority + 1;i<count.length;i++) {
                if(count[i] > 0) {
                    queue.offer(cur);
                    continue outer;
                }
            }
            
            order++;
            count[cur.priority]--;
            
            if(cur.location == location) {
                break;
            }
        }
        
        return order;
    }
}

class Node {
    int priority;
    int location;
    
    Node(int priority, int location) {
        this.priority = priority;
        this.location = location;
    }
}