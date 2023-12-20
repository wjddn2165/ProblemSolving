import sys

input = sys.stdin.readline
N = int(input())
l, r = map(int, input().split())
M = int(input())

dp = [[[1000 for _ in range(N)] for _ in range(N)] for _ in range(M + 1)]
dp[0][l - 1][r - 1] = 0

for k in range(M):
    m = int(input()) - 1
    for i in range(N):
        for j in range(N):
            dp[k + 1][m][j] = min(dp[k + 1][m][j], dp[k][i][j] + abs(i - m))
            dp[k + 1][i][m] = min(dp[k + 1][i][m], dp[k][i][j] + abs(j - m))

answer = 1000

for i in range(N):
    for j in range(N):
        answer = min(answer, dp[M][i][j])

print(answer)