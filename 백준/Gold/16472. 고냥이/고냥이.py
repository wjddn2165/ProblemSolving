import sys

input = sys.stdin.readline


def solution():
    n = int(input())
    s = input().rstrip()

    left = 0
    right = 0

    cur_count = 0
    count_list = [0] * 26
    max_length = 0

    while right <= len(s):
        if cur_count <= n:
            max_length = max(max_length, right - left)

            if right == len(s):
                break

            idx = ord(s[right]) - ord('a')
            count_list[idx] += 1
            if count_list[idx] == 1:
                cur_count += 1
            right += 1
        else:
            while cur_count > n:
                idx = ord(s[left]) - ord('a')
                count_list[idx] -= 1
                if count_list[idx] == 0:
                    cur_count -= 1
                left += 1

    print(max_length)


if __name__ == '__main__':
    solution()
