import sys
input = sys.stdin.readline

def prefix_function(s):
    n = len(s)
    pi = [0] * n
    j = 0

    for i in range(1, n):
        while j > 0 and s[i] != s[j]:
            j = pi[j - 1]

        if s[i] == s[j]:
            j += 1
            pi[i] = j

    return pi[-1]

def solution():
    s, c = input().split()
    c = int(c)

    max_prefix = prefix_function(s)

    print(len(s) * c - (c - 1) * max_prefix)

solution()