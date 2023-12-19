import sys

input = sys.stdin.readline

N, M = map(int, input().split())

if M == 0:
    print(0)
    sys.exit(0)

arr = []
for _ in range(N):
    arr.append(int(input()))

arr.sort()

l,r = 0, 0
result = 2000000001

while r < len(arr):
    diff = arr[r] - arr[l]
    if diff >= M:
        result = min(result, diff)
        l += 1
    else:
        r += 1

print(result)