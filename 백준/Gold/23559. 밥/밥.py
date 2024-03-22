import sys
from heapq import heappop, heappush

input = sys.stdin.readline


def solution():
    N, X = map(int, input().split())
    pq = []
    answer = 0

    X -= 1000 * N

    for _ in range(N):
        A, B = map(int, input().split())
        heappush(pq, -(A - B))
        answer += B

    while pq and X >= 4000:
        max_score = -heappop(pq)
        if max_score <= 0:
            continue

        X -= 4000
        answer += max_score

    print(answer)


if __name__ == '__main__':
    solution()