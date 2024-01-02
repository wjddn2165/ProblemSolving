import sys
input = sys.stdin.readline

max_dist = int(input())
repair_count = int(input())
repair_dist = list(map(int, input().split()))
repair_time = list(map(int, input().split()))
repair = [[0, 0]] + [[x, y] for x, y in zip(repair_dist, repair_time)] + [[repair_dist[-1], 0]]
INF = 2**32 - 1

dp = [INF for _ in range(repair_count + 2)]
dp[0] = 0
for i in range(1, repair_count + 2):
    total = 0
    for j in range(i - 1, -1, -1):
        total += repair[j + 1][0]
        if total > max_dist:
            break

        dp[i] = min(dp[i], dp[j] + repair[i][1])

cur, cnt = dp[-1], 0
trace = []
for i in range(repair_count, 0, -1):
    if cur == dp[i]:
        trace.append(i)
        cur -= repair[i][1]
        cnt += 1

trace.reverse()

print(dp[-1])
print(cnt)
print(*trace)