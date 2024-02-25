import sys
from heapq import heappop, heappush

input = sys.stdin.readline


def solution():
    n = int(input())
    arr = [list(map(int, (input().split()))) for _ in range(n)]

    arr.sort(key=lambda x: (x[1], -x[0]))

    pq = []

    for p, d in arr:
        if len(pq) < d:
            heappush(pq, p)
        elif pq[0] < p:
            heappop(pq)
            heappush(pq, p)

    print(sum(pq))


solution()