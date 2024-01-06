import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
edge = [[] for _ in range(N + 1)]

degree = [0] * (N + 1)
dp = [0] * (N + 1)

for _ in range(M):
    A, B = map(int, input().split())
    degree[B] += 1
    edge[A].append(B)

queue = deque()

for i in range(1, N + 1):
    if degree[i] == 0:
        queue.append([i, 1])

while queue:
    num, time = queue.popleft()

    dp[num] = max(dp[num], time)

    for next in edge[num]:
        degree[next] -= 1
        if degree[next] == 0:
            queue.append([next, time + 1])

print(*dp[1:])