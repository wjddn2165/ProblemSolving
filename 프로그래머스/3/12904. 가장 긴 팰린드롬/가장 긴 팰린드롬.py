import sys
sys.setrecursionlimit(10**6)

def dfs(left, right, s, dp):    
    if left == right:
        return 1
    
    if left == right - 1:
        if s[left] == s[right]:
            dp[left][right] = 2
            return 2
        else:
            dp[left][right] = 0
            return 0
    
    if dp[left][right] != -1:
        return dp[left][right]
    
    dp[left][right] = 0
    
    next = dfs(left + 1, right - 1, s, dp)
    
    if s[left] == s[right] and next != 0:
        dp[left][right] = next + 2
    else:
        dfs(left + 1, right, s, dp)
        dfs(left, right - 1, s, dp)
    
    return dp[left][right]

def solution(s):
    n = len(s)
    dp = [[-1 for _ in range(n)] for _ in range(n)]
    
    dfs(0, n - 1, s, dp)
    
    answer = 1
    for i in range(n - 1):
        for j in range(i + 1, n):
            answer = max(answer, dp[i][j])
    
    return answer