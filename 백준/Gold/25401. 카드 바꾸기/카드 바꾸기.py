import sys
input = sys.stdin.readline

def check(arr, start, diff):
    ans = 0
    for i in range(len(arr)):
        if arr[i] != start:
            ans += 1

        start += diff

    return ans
def solution():
    n = int(input())
    arr = list(map(int, input().split()))

    ans = int(1e9)
    for i in range(n - 1):
        for j in range(i + 1, n):
            diff = (arr[j] - arr[i]) / (j - i)
            if diff != int(diff):
                continue

            ans = min(ans, check(arr, arr[i] - diff * i, diff))

    print(ans)

solution()