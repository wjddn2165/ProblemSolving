import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

order = list(map(int, input().split()))
dp = [[[-1 for _ in range(5)] for _ in range(5)] for _ in range(len(order))]

def getWeight(pos1, pos2):
    if pos1 == 0:
        return 2

    if pos1 == pos2:
        return 1

    if (pos1 + pos2) % 2 == 0:
        return 4

    return 3

def dfs(left, right, idx):
    if order[idx] == 0:
        return 0

    if idx > 0 and left == right:
        return 10**6

    if dp[idx][left][right] != -1:
        return dp[idx][left][right]

    dp[idx][left][right] = min(dfs(order[idx], right, idx + 1) + getWeight(left, order[idx]),
                               dfs(left, order[idx], idx + 1) + getWeight(right, order[idx]))

    return dp[idx][left][right]


print(dfs(0, 0, 0))