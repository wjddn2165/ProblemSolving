import sys
input = sys.stdin.readline
T = int(input())
k = int(input())
dp = [0 for _ in range(T + 1)]
dp[0] = 1

for _ in range(k):
    p, n = map(int, input().split())
    temp = dp[:]
    for i in range(1, n + 1):
        if p * i > T:
            break
        for j in range(p * i, T + 1):
            dp[j] += temp[j - p * i]
print(dp[-1])