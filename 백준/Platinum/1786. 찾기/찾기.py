import sys
input = sys.stdin.readline

def prefix_function(p):
    n = len(p)
    pi = [0] * n
    j = 0
    for i in range(1, n):
        while j > 0 and p[j] != p[i]:
            j = pi[j - 1]

        if p[j] == p[i]:
            j += 1

        pi[i] = j

    return pi

def solution():
    t = input().rstrip()
    p = input().rstrip()

    n = len(t)
    m = len(p)

    pi = prefix_function(p)
    res = []

    j = 0
    for i in range(n):
        while j > 0 and t[i] != p[j]:
            j = pi[j - 1]

        if t[i] == p[j]:
            if j == m - 1:
                res.append(i - m + 2)
                j = pi[j]
            else:
                j += 1

    print(len(res))
    print(*res)

solution()