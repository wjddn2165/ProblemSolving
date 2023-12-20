import sys
input = sys.stdin.readline
T = int(input())
k = int(input())
dp = [0 for _ in range(T + 1)]
dp[0] = 1

for i in range(k):
    p, n = map(int, input().split())
    for j in range(T, -1, -1):
        for l in range(1, n + 1):
            if j >= l * p:
                dp[j] += dp[j - l * p]

print(dp[-1])