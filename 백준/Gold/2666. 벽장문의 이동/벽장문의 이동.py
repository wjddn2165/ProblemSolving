import sys
input = sys.stdin.readline

nums = []
N = int(input())
l, r = map(int, input().split())
M = int(input())
for _ in range(M):
    nums.append(int(input()) - 1)
dp = [[[-1 for _ in range(N)] for _ in range(N)] for _ in range(M + 1)]

def rec(left, right, idx):
    if idx == M:
        return 0

    m = nums[idx]

    if dp[idx][left][right] != -1:
        return dp[idx][left][right]

    dp[idx][left][right] = min(abs(left - m) + rec(m, right, idx + 1), abs(right - m) + rec(left, m, idx + 1))
    return dp[idx][left][right]


print(rec(l - 1, r - 1, 0))