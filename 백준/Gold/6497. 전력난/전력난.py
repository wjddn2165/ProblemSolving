import sys
from heapq import heappop, heappush
input = sys.stdin.readline

parent = []
rank = []

class Edge:
    def __init__(self, u, v, w):
        self.u = u
        self.v = v
        self.w = w

    def __lt__(self, other):
        return self.w < other.w

def find(node):
    if parent[node] == node:
        return node

    parent[node] = find(parent[node])
    return parent[node]

def union(node1, node2):
    root1 = find(node1)
    root2 = find(node2)

    if root1 == root2:
        return False

    if rank[root1] == rank[root2]:
        parent[root1] = root2
        rank[root2] += 1
    elif rank[root1] > rank[root2]:
        parent[root2] = root1
    else:
        parent[root1] = root2

    return True



def solution():
    while True:
        m, n = map(int, input().split())

        if m == 0 and n == 0:
            break

        pq = []
        parent.clear()
        rank.clear()
        for i in range(m):
            parent.append(i)
            rank.append(0)

        total = 0

        for i in range(n):
            u, v, w = map(int, input().split())
            total += w
            heappush(pq, Edge(u, v, w))

        count = 0

        while pq or count < m - 1:
            edge = heappop(pq)
            if union(edge.u, edge.v):
                count += 1
                total -= edge.w

        print(total)

solution()