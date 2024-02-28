import sys
import re
input = sys.stdin.readline

def solution():
    n = int(input())
    p = re.compile("^(100+1+|01)+$")
    for i in range(n):
        if p.match(input().rstrip()):
            print('YES')
        else:
            print('NO')

solution()