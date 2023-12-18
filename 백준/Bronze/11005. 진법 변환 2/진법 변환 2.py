import sys
input = sys.stdin.readline
s = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'
def rec(N, B):
    q, r = divmod(N, B)
    if q == 0:
        return s[r]
    else:
        return rec(q, B) + s[r]


N, B = map(int, input().split())
print(rec(N, B))