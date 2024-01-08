import sys
input = sys.stdin.readline

def solution():
    N, M, K = map(int, input().split())

    dp = [[0 for _ in range(M + 1)] for _ in range(N + 1)]

    for i in range(N + 1):
        dp[i][0] = 1

    for j in range(M + 1):
        dp[0][j] = 1

    for i in range(1, N + 1):
        for j in range(1, M + 1):
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1]

    if K > dp[N][M]:
        print(-1)
        return

    answer = []

    for _ in range(N + M):
        if N == 0:
            answer.append('z')
            continue
        if M == 0:
            answer.append('a')
            continue

        if dp[N - 1][M] >= K:
            answer.append('a')
            N -= 1
        else:
            answer.append('z')
            K -= dp[N-1][M]
            M -= 1

    print(''.join(answer))

solution()