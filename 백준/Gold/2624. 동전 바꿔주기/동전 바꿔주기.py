import sys
input = sys.stdin.readline
T = int(input())
k = int(input())
p = []
n = []
for _ in range(k):
    pi, ni = map(int, input().split())
    p.append(pi)
    n.append(ni)

dp = [0 for _ in range(T + 1)]
dp[0] = 1

for i in range(k):
    for j in range(T, -1, -1):
        for l in range(1, n[i] + 1):
            if j >= l * p[i]:
                dp[j] += dp[j - l * p[i]]

print(dp[T])