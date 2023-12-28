import sys
input = sys.stdin.readline

N, M, H = map(int, input().split())
dp = [0 for _ in range(H + 1)]
dp[0] = 1
for _ in range(N):
    h = list(map(int, input().split()))
    temp = dp[:]
    for h_i in h:
        for i in range(h_i, H + 1):
            dp[i] += temp[i - h_i]
            dp[i] %= 10007
        
print(dp[H])