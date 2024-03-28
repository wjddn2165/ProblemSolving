import sys

input = sys.stdin.readline

def solution():
    src = input().rstrip()
    dst = input().rstrip()

    if dst in src:
        print('go')
    else:
        print('no')


if __name__ == '__main__':
    solution()
