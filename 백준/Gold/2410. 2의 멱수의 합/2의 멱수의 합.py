import sys

input = sys.stdin.readline
N = int(input())

dp = [0 for _ in range(N + 1)]
dp[0] = 1

v = 1
MOD = 1000000000

while v <= N:
    for i in range(v, N + 1):
        dp[i] += dp[i - v]
        dp[i] %= MOD
    v *= 2

print(dp[N])