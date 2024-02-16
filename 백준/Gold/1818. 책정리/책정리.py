import sys

input = sys.stdin.readline

def lower_bound(arr, k):
    lo = 0
    hi = len(arr) - 1

    while lo <= hi:
        mid = (lo + hi) >> 1
        if arr[mid] >= k:
            hi = mid - 1
        else:
            lo = mid + 1

    return lo

def solution():
    n = int(input())
    dp = [-1]

    for x in map(int, input().split()):
        if dp[-1] < x:
            dp.append(x)
        else:
            dp[lower_bound(dp, x)] = x

    print(n - len(dp) + 1)

solution()