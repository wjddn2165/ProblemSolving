import sys
input = sys.stdin.readline
def solution():
    while True:
        n = int(input())
        if n == 0:
            break

        sum = 0
        answer = -10001

        for i in range(n):
            next = int(input())
            if sum < 0:
                sum = next
            else:
                sum += next

            answer = max(answer, sum)

        print(answer)

solution()