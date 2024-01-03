import sys

sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

T, W = map(int, input().split())
pos = [int(input()) for _ in range(T)]
dp = [[[-1 for _ in range(2)] for _ in range(W + 1)] for _ in range(T)]


def dfs(depth, cur, count):
    if depth == T:
        return 0

    if count > W:
        return 0

    if dp[depth][count][cur] != -1:
        return dp[depth][count][cur]

    dp[depth][count][cur] = max(dfs(depth + 1, (cur + 1) % 2, count + 1), dfs(depth + 1, cur, count))
    if pos[depth] == cur + 1:
        dp[depth][count][cur] += 1

    return dp[depth][count][cur]


print(max(dfs(0, 0, 0), dfs(0, 1, 1)))