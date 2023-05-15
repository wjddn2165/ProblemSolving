import java.util.*;

class Solution {

    int[] parent;
    String[] board;

    // 2차원 배열을 1차원으로 풀이하기 위한 좌표 변환 함수
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

        // r1, c1의 대표 셀값이 빈 셀일 경우, r2, c2 를 대표값으로 설정
        // 그 외 나머지 경우는 r1, c1 을 대표값으로 설정
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
                
                // 처음에는 unmerge 시 find(j) == root 인 모든 셀에 대해서 부모를 자기 자신으로 초기화 했지만, 이렇게 풀게되면 다른 노드가 루트로 가는 과정에서 중간 연결 다리가 끊어질 수 있음. 따라서 root(j) == root 인 셀을 미리 다 표시해 두었다가 한번에 업데이트 해주었음.
                for(int j=1;j<parent.length;j++) {
                    find(j);
                }
                
                for(int j=1;j<parent.length;j++) {
                    if(find(j) == root) {
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