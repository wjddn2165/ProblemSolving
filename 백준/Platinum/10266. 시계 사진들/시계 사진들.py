import sys
input = sys.stdin.readline

clock1 = [0] * 360000
clock2 = [0] * 360000
def prefix_function(s):
    n = len(s)
    j = 0
    pi = [0] * n

    for i in range(1, n):
        while j > 0 and s[i] != s[j]:
            j = pi[j - 1]

        if s[i] == s[j]:
            j += 1
            pi[i] = j

    return pi

def solution():
    n = int(input())
    for i, j in zip(map(int, input().split()), map(int, input().split())):
        clock1[i] = 1
        clock2[j] = 1

    clock1.extend(clock1)
    pi = prefix_function(clock2)

    j = 0
    for i in range(720000):
        while j > 0 and clock1[i] != clock2[j]:
            j = pi[j - 1]

        if clock1[i] == clock2[j]:
            j += 1
            if j == 360000:
                print('possible')
                return

    print('impossible')

solution()