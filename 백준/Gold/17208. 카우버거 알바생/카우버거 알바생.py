import sys
input = sys.stdin.readline

N, M, K = map(int, input().split())
dp = [[0 for _ in range(K + 1)] for _ in range(M + 1)]

for _ in range(N):
    x, y = map(int, input().split())
    for i in range(M, x - 1, - 1):
        for j in range(K, y - 1, - 1):
            dp[i][j] = max(dp[i][j], dp[i - x][j - y] + 1)

print(dp[M][K])