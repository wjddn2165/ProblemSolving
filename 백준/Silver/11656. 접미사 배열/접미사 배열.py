import sys

input = sys.stdin.readline


def solution():
    s = input().rstrip()
    results = []
    for i in range(len(s)):
        results.append(s[i:])

    results.sort()
    for result in results:
        print(result)


if __name__ == '__main__':
    solution()