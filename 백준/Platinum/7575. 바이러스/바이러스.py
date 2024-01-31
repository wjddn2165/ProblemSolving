import sys
input = sys.stdin.readline

def kmp(s, p):
    n = len(s)
    m = len(p)
    j = 0
    pi = [0] * n

    for i in range(1, m):
        while j > 0 and p[i] != p[j]:
            j = pi[j - 1]

        if p[i] == p[j]:
            j += 1
            pi[i] = j

    j = 0
    for i in range(n):
        while j > 0 and s[i] != p[j]:
            j = pi[j - 1]

        if s[i] == p[j]:
            j += 1
            if j == m:
                return True

    return False



def solution():
    n, k = map(int, input().split())
    program = []
    for i in range(n):
        m = int(input())
        program_i = [int(x) for x in input().split()]
        program.append(program_i)

    for i in range(len(program[0]) - k + 1):
        p = program[0][i:i+k]
        find = True
        for j in range(1, n):
            if kmp(program[j], p) or kmp(program[j][::-1], p):
                continue
            else:
                find = False
                break

        if find:
            print('YES')
            return

    print('NO')

solution()