import sys
input = sys.stdin.readline

def solution():
    n = int(input())
    total = 0
    arr = []
    for i in range(n):
        X, A = map(int, input().split())
        arr.append([X, A])
        total += A

    arr.sort(key=lambda x: x[0])

    temp = 0
    for i in range(n):
        temp += arr[i][1]
        if temp >= total / 2:
            print(arr[i][0])
            return

solution()