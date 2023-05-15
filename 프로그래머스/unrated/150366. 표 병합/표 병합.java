import java.util.*;

class Solution {

    int[] parent;
    String[] board;

    int transform(int r, int c) {
        return (r - 1) * 50 + c;
    }

    int find(int num) {
        if (parent[num] == num) {
            return num;
        }

        return parent[num] = find(parent[num]);
    }

    void union(int A, int B) {
        int rootA = find(A);
        int rootB = find(B);

        if (rootA == rootB) return;

        // r1, c1의 대표 셀값이 빈 셀일 경우, r2, c2 를 대표 값으로 설정
        if (board[rootA] == null) {
            parent[rootA] = parent[rootB];
        } else {
            parent[rootB] = parent[rootA];
        }
    }

    public String[] solution(String[] commands) {
        List<String> result = new ArrayList<>();

        board = new String[2501];
        parent = new int[2501];

        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < commands.length; i++) {
            String[] command = commands[i].split(" ");

            if (command[0].equals("UPDATE")) {
                if (command.length == 4) {
                    int r = Integer.parseInt(command[1]);
                    int c = Integer.parseInt(command[2]);

                    String value = command[3];
                    
                    int idx = transform(r, c);
                    int root = find(idx);
                    
                    board[root] = value;
                } else {
                    String value1 = command[1];
                    String value2 = command[2];
                    
                    for (int j = 1; j < board.length; j++) {
                        if (board[j] != null && board[j].equals(value1)) {
                            board[j] = value2;
                        }
                    }
                }
            }

            if (command[0].equals("MERGE")) {
                int r1 = Integer.parseInt(command[1]);
                int c1 = Integer.parseInt(command[2]);
                int r2 = Integer.parseInt(command[3]);
                int c2 = Integer.parseInt(command[4]);

                int idx1 = transform(r1, c1);
                int idx2 = transform(r2, c2);

                union(idx1, idx2);
            }

            if (command[0].equals("UNMERGE")) {
                int r = Integer.parseInt(command[1]);
                int c = Integer.parseInt(command[2]);

                int idx = transform(r, c);
                int root = find(idx);
                String rootValue = board[root];
                
                boolean[] flag = new boolean[2501];

                for(int j=1;j<parent.length;j++) {
                    if(find(j) == root) {
                        flag[j] = true;
                    }
                }
                
                for(int j=1;j<parent.length;j++) {
                    if(flag[j]) {
                        parent[j] = j;
                        board[j] = null;
                    }
                }
                
                board[idx] = rootValue;
            }

            if (command[0].equals("PRINT")) {
                int r = Integer.parseInt(command[1]);
                int c = Integer.parseInt(command[2]);

                int idx = transform(r, c);
                int root = find(idx);

                if (board[root] == null) {
                    result.add("EMPTY");
                } else { 
                    result.add(board[root]);
                }
            }
        }

        String[] answer = new String[result.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}