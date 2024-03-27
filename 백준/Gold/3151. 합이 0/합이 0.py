import sys

input = sys.stdin.readline


def lower_bound(arr, lo, hi, k):
    while lo <= hi:
        mid = (lo + hi) >> 1
        if arr[mid] >= k:
            hi = mid - 1
        else:
            lo = mid + 1

    return lo


def upper_bound(arr, lo, hi, k):
    while lo <= hi:
        mid = (lo + hi) >> 1
        if arr[mid] <= k:
            lo = mid + 1
        else:
            hi = mid - 1

    return lo


def solution():
    n = int(input())
    arr = [int(x) for x in input().split()]

    arr.sort()

    answer = 0

    for i in range(n - 2):
        for j in range(i + 1, n - 1):
            target = -(arr[i] + arr[j])
            answer += upper_bound(arr, j + 1, n - 1, target) - lower_bound(arr, j + 1, n - 1, target)

    print(answer)


if __name__ == '__main__':
    solution()
