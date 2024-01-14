import sys
sys.setrecursionlimit(10**6)
INF = 987654321


def dfs(cur, idx, temperature, t1, t2, cost, onboard, dp):
    if idx == len(onboard):
        return 0
    
    if onboard[idx] == 1 and (cur < t1 or cur > t2):
        return INF  + 1
    
    if cur < 0 or cur > 50:
        return INF + 1
    
    if dp[cur][idx] != INF:
        return dp[cur][idx]
    
    if cur == temperature:
        dp[cur][idx] = min(dfs(cur + 1, idx + 1, temperature,  t1, t2, cost, onboard, dp) + cost[2],
                      dfs(cur, idx + 1, temperature,  t1, t2, cost, onboard, dp),
                      dfs(cur - 1, idx + 1, temperature,  t1, t2, cost, onboard, dp) + cost[0])
    else:
        dp[cur][idx] = min(dfs(cur + 1, idx + 1, temperature,  t1, t2, cost, onboard, dp) + cost[2],
                      dfs(cur, idx + 1, temperature,  t1, t2, cost, onboard, dp) + cost[1],
                      dfs(cur - 1, idx + 1, temperature,  t1, t2, cost, onboard, dp) + cost[0])
        
    return dp[cur][idx]
    

def solution(temperature, t1, t2, a, b, onboard):
    temperature += 10
    t1 += 10
    t2 += 10
    
    # 온도를 내릴 때, 유지할 때, 올릴 때 발생하는 비용
    cost = [0, b, 0]
    
    if temperature < t1:
        cost[2] = a
    else:
        cost[0] = a
    
    dp = [[INF for _ in range(1000)] for _ in range(51)]
    
    return dfs(temperature, 0, temperature, t1, t2, cost, onboard, dp)
