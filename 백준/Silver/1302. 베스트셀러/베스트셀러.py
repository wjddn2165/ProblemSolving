import sys
from collections import defaultdict

input = sys.stdin.readline


def solution():
    n = int(input())
    count_map = defaultdict(int)

    for i in range(n):
        word = input().rstrip()
        count_map[word] = count_map[word] + 1

    max_count = 0

    for word in count_map.keys():
        if count_map[word] > max_count:
            max_count = count_map[word]

    result = []
    for word in count_map.keys():
        if count_map[word] == max_count:
            result.append(word)

    result.sort()

    print(result[0])


if __name__ == '__main__':
    solution()
