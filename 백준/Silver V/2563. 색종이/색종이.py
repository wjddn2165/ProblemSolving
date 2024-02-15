import sys
input = sys.stdin.readline
def solution():
   n = int(input())
   board = [[False for _ in range(101)] for _ in range(101)]

   answer = 0

   for i in range(n):
       r, c = map(int, input().split())
       for j in range(r, r + 10):
           for k in range(c, c + 10):
               if not board[j][k]:
                   board[j][k] = True
                   answer += 1

   print(answer)

solution()