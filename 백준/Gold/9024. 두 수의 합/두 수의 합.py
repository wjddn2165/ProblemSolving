import sys

input = sys.stdin.readline


def solution():
    t = int(input())
    answer = []

    while t:
        t -= 1
        n, k = map(int, input().split())
        arr = [int(x) for x in input().split()]
        arr.sort()

        left = 0
        right = n - 1
        count = 0
        min_diff = int(1e9)

        while left < right:
            total = arr[left] + arr[right]
            diff = abs(k - total)

            if min_diff > diff:
                min_diff = diff
                count = 1
            elif min_diff == diff:
                count += 1

            if total >= k:
                right -= 1
            elif total < k:
                left += 1

        answer.append(count)

    print('\n'.join(map(str, answer)))


if __name__ == '__main__':
    solution()
