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
    n = int(input())
    str = input().rstrip()
    pi = prefix_function(str)

    print(n - pi[n - 1])

solution()