INF = 1000
maxAlp = 0
maxCop = 0

def dfs(alp, cop, problems, dp):
    if alp >= maxAlp and cop >= maxCop:
        return 0
    
    if dp[alp][cop] != INF:
        return dp[alp][cop]
    
    if alp < maxAlp:
        dp[alp][cop] = min(dp[alp][cop], dfs(alp + 1, cop, problems, dp) + 1)
    
    if cop < maxCop:
        dp[alp][cop] = min(dp[alp][cop], dfs(alp, cop + 1, problems, dp) + 1)
    
    for problem in problems:
        if alp >= problem[0] and cop >= problem[1]:
            dp[alp][cop] = min(dp[alp][cop], dfs(min(maxAlp, alp + problem[2]), min(maxCop, cop + problem[3]), problems, dp) + problem[4])
    
    return dp[alp][cop]

def solution(alp, cop, problems):
    global maxAlp, maxCop
    
    for problem in problems:
        maxAlp = max(maxAlp, problem[0])
        maxCop = max(maxCop, problem[1])
    
    dp = [[INF for _ in range(maxCop + 1)] for _ in range(maxAlp + 1)]
    
    return dfs(min(maxAlp, alp), min(maxCop, cop), problems, dp)