import sys
input = sys.stdin.readline

def solution():
    n, k = map(int, input().split())
    arr = [int(x) for x in input().split()]

    answer = -int(1e9)

    left = 0
    right = 1
    sum = arr[0]

    while right <= len(arr):
        if right - left < k:
            if right == len(arr):
                break
            sum += arr[right]
            right += 1
        else:
            answer = max(answer, sum)
            sum -= arr[left]
            left += 1

    print(answer)

solution()