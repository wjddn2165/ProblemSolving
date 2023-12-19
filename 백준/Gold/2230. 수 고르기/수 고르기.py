import sys
input = sys.stdin.readline

def lowerBound(arr, left, right, k):
    v = arr[left - 1]

    while left <= right:
        mid = (left + right) >> 1
        if arr[mid] - v >= k:
            right = mid - 1
        else:
            left = mid + 1

    if(left >= len(arr)):
        return 2000000001
    return arr[left] - v


N, M = map(int, input().split())

arr = []
for _ in range(N):
    arr.append(int(input()))

arr.sort()

result = 2000000001

for i in range(len(arr) - 1):
    result = min(result, lowerBound(arr, i + 1, len(arr) - 1, M))

print(result)