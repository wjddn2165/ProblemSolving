import sys
input = sys.stdin.readline

def solution():
    n = int(input())
    arr = [int(input()) for _ in range(n)]

    stack = []
    answer = 0

    for x in arr:
        while len(stack) > 0 and stack[-1] <= x:
            stack.pop()

        answer += len(stack)
        stack.append(x)

    print(answer)

solution()