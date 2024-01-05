import sys
input = sys.stdin.readline

N = int(input())
arr = list(map(int, input().split()))

# 1번 개미굴 선택
dp1 = [[0, 0] for _ in range(N)]
# 1번 쪽방 선택
dp2 = [[0, 0] for _ in range(N)]

dp1[0][1] = 1
dp2[0][0] = arr[0]

for i in range(1, N):
    dp1[i][1] = dp1[i - 1][0] + 1
    dp1[i][0] = max(dp1[i - 1][0], dp1[i - 1][1]) + arr[i]

    dp2[i][1] = dp2[i - 1][0] + 1
    dp2[i][0] = max(dp2[i - 1][0], dp2[i - 1][1]) + arr[i]

print(max(dp1[N - 1][0], dp2[N - 1][0], dp2[N - 1][1]))