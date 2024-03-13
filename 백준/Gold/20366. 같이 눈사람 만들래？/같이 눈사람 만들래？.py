import sys

input = sys.stdin.readline


def solution():
    n = int(input())
    arr = [int(x) for x in input().split()]

    arr.sort()

    answer = int(1e9)

    for i in range(n - 3):
        for j in range(i + 3, n):
            left = i + 1
            right = j - 1

            while left < right:
                sum1 = arr[i] + arr[j]
                sum2 = arr[left] + arr[right]

                answer = min(answer, abs(sum1 - sum2))

                if sum1 > sum2:
                    left += 1
                else:
                    right -= 1

    print(answer)


if __name__ == '__main__':
    solution()
