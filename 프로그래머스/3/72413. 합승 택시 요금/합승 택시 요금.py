from collections import defaultdict
from heapq import heappush, heappop

INF = 10**9

def dijkstra(n, start, graph):
    dist = [INF] * (n + 1)
    
    pq = []
    heappush(pq, (0, start))
    dist[start] = 0
    
    while pq:
        uw, u = heappop(pq)
        
        if uw > dist[u]:
            continue
        
        for v, w in graph[u]:
            vw = uw + w
            if dist[v] > vw:
                dist[v] = vw
                heappush(pq, (vw, v))
        
    return dist

def solution(n, s, a, b, fares):
    graph = defaultdict(list)
    
    for u, v, w in fares:
        graph[u].append((v, w))
        graph[v].append((u, w))
    
    dist_A = dijkstra(n, a, graph)
    dist_B = dijkstra(n, b, graph)
    dist_S = dijkstra(n, s, graph)
    
    answer = INF
    
    for i in range(1, n + 1):
        answer = min(answer, dist_S[i] + dist_A[i] + dist_B[i])
    
    return answer
