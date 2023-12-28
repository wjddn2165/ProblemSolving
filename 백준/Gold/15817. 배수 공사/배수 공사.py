import sys
input = sys.stdin.readline

n, x = map(int, input().split())
dp = [0 for _ in range(x + 1)]
for _ in range(n):
    w, c, = map(int, input().split())
    temp = dp[:]
    for i in range(1, c + 1):
        if w * i > x:
            break

        dp[w * i] += 1
        for j in range(w * i + 1, x + 1):
            dp[j] += temp[j - w * i]

print(dp[-1])