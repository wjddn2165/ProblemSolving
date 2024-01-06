import sys
input = sys.stdin.readline

N, M, L = map(int, input().split())
pos = [int(input()) for _ in range(M)]
counts = [int(input()) for _ in range(N)]

def decide(k, c):
    count = 0
    cur = 0
    for p in pos:
        if p - cur >= k and L - p >= k:
            cur = p
            count += 1
            if count == c:
                return True

    return False

def upper_bound(c):
    lo, hi = 1, L//2
    while lo <= hi:
        mid = (lo + hi) >> 1
        if decide(mid, c):
            lo = mid + 1
        else:
            hi = mid - 1

    return hi

for c in counts:
    print(upper_bound(c))