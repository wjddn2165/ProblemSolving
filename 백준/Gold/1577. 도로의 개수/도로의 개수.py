import sys
input = sys.stdin.readline

n, m = map(int, input().split())
k = int(input())

dp = [[0 for _ in range(m + 1)] for _ in range(n + 1)]
edge = [[False for _ in range(2 * m + 1)] for _ in range(2 * n + 1)]
for _ in range(k):
    a, b, c, d = map(int, input().split())
    edge[a + c][b + d] = True

dp[0][0] = 1

for i in range(1, n + 1):
    if edge[i * 2 - 1][0]:
        break

    dp[i][0] = dp[i - 1][0]

for i in range(1, m + 1):
    if edge[0][i * 2 - 1]:
        break

    dp[0][i] = dp[0][i - 1]

for i in range(1, n + 1):
    for j in range(1, m + 1):
        if not edge[2 * i - 1][2 * j]:
            dp[i][j] += dp[i - 1][j]

        if not edge[2 * i][2 * j - 1]:
            dp[i][j] += dp[i][j - 1]

print(dp[n][m])