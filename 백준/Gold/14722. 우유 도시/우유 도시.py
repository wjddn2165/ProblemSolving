import sys
input = sys.stdin.readline

n = int(input())
m = [list(map(int,input().split())) for _ in range(n)]
d = [[0]*(n+1) for _ in range(n+1)]
for i in range(1, n+1):
    for j in range(1, n+1):
        d[i][j] = max(d[i-1][j],d[i][j-1])
        if d[i][j] % 3 == m[i-1][j-1]:
            d[i][j] += 1
print(d[-1][-1])