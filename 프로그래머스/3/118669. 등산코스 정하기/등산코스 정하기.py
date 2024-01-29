from collections import defaultdict
from heapq import heappush, heappop

INF = int(1e9)

def solution(n, paths, gates, summits):
    graph = defaultdict(list)
    
    for u, v, w in paths:
        graph[u].append((v, w))
        graph[v].append((u, w))
    
    summit_set = set(summits)
    gate_set = set(gates)
    
    pq = []
    dist = [INF] * (n + 1)

    for gate in gates:
        heappush(pq, (0, gate))
        dist[gate] = 0
        
    while pq:
        cur = heappop(pq)
        
        if cur[0] > dist[cur[1]]:
            continue
        
        if cur[1] in summit_set:
            continue
        
        for v, w in graph[cur[1]]:
            if v in gate_set:
                continue
                
            vw = max(cur[0], w)
            if dist[v] > vw:
                dist[v] = vw
                heappush(pq, (vw, v))
        
    answer = [[summit, dist[summit]] for summit in summits]
    answer.sort(key=lambda x:(x[1], x[0]))
    
    return answer[0]