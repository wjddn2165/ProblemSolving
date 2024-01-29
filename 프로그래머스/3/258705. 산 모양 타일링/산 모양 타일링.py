MOD = 10007

def solution(n, tops):
    dp = [0] * (2 * n + 2)
    dp[0] = 1
    dp[1] = 1
    
    for i in range(2, 2 * n + 2):
        # 짝수번 째 삼각형
        if i % 2 == 0:
            if tops[(i // 2) - 1] == 1:
                dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % MOD
            else:
                dp[i] = (dp[i - 1] + dp[i - 2]) % MOD
        # 홀수번 째 삼각형
        else:
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD
        
    return dp[-1]