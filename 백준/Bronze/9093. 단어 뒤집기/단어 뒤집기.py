import sys

input = sys.stdin.readline


def solution():
    t = int(input())
    answer = []
    while t:
        t-=1
        s = input().rstrip()
        words = s.split()
        reversed = []
        for word in words:
            reversed.append(word[::-1])

        answer.append(' '.join(reversed))

    print('\n'.join(answer))

if __name__ == '__main__':
    solution()