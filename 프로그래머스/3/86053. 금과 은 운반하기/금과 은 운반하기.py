def decide(time, a, b, g, s, w, t):
    total_gold = 0
    total_silver = 0
    total = 0
    
    n = len(g)
    
    for i in range(n):
        count = time // (2 * t[i])
        if time % (2 * t[i]) >= t[i]:
            count += 1
        
        tmp = min(w[i] * count, g[i] + s[i])
        total += tmp
        total_gold += min(g[i], tmp)
        total_silver += min(s[i], tmp)
    
    return total >= a + b and total_gold >= a and total_silver >= b
    
def solution(a, b, g, s, w, t):
    lo = 0
    hi = 10**15
    
    while lo <= hi:
        mid = (lo + hi) >> 1
        if decide(mid, a, b, g, s, w, t):
            hi = mid - 1
        else:
            lo = mid + 1
    
    return lo