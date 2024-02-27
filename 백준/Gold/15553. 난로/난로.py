import sys
from heapq import heappop, heappush

input = sys.stdin.readline


def solution():
    n, k = map(int, input().split())
    arr = [int(input()) for _ in range(n)]

    pq = []

    for i in range(n - 1):
        heappush(pq, arr[i] - arr[i + 1])

    answer = arr[n - 1] - arr[0] + k

    for i in range(k - 1):
        answer += heappop(pq)

    print(answer)

solution()