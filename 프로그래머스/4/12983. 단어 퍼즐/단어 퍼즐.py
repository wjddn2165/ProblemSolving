import sys
sys.setrecursionlimit(10**6)
INF = int(1e9)

def dfs(strs, t, idx, dp):
    if idx == len(t):
        return 0
    
    if dp[idx] != INF:
        return dp[idx]
    
    dp[idx] = INF + 1
        
    for i in range(1, 6):
        if idx + i > len(t):
            continue;
        
        if t[idx:idx + i] in strs:
            dp[idx] = min(dp[idx], dfs(strs, t, idx + i, dp) + 1)
    
    return dp[idx]
            
def solution(strs, t):
    dp = [INF] * len(t)
    strs = set(strs)
    answer = dfs(strs, t, 0, dp)
    return -1 if answer == INF + 1 else answer