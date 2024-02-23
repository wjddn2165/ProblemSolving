import sys
from heapq import heappop, heappush
input = sys.stdin.readline

parent = []
min_count = 0
max_count = 0

def find(node):
    if parent[node] == node:
        return node

    root = find(parent[node])
    parent[node] = root
    return root

def union(node1, node2):
    root1 = find(node1)
    root2 = find(node2)

    if root1 == root2:
        return False

    parent[root1] = root2
    return True



def solution():
    global min_count, max_count

    n, m = map(int, input().split())
    min_edges = []
    max_edges = []

    for i in range(m + 1):
        a, b, c = map(int, input().split())
        heappush(min_edges, (c ^ 1, a, b))
        heappush(max_edges, (c, a, b))

    for i in range(n + 1):
        parent.append(i)

    edge_count = 0
    while min_edges or edge_count < n - 1:
        w, u, v = heappop(min_edges)
        if union(u, v):
            edge_count += 1
            min_count += w

    for i in range(n + 1):
        parent[i] = i

    edge_count = 0
    while max_edges or edge_count < n - 1:
        w, u, v = heappop(max_edges)
        if union(u, v):
            edge_count += 1
            max_count += w ^ 1

    print(max_count ** 2 - min_count ** 2)

solution()