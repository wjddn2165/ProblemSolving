import sys

input = sys.stdin.readline

def solution():
    print(sum([x for x in range(1, int(input()) + 1)]))



if __name__ == '__main__':
    solution()