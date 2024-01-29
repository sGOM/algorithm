def solution(n):
    MOD = 1234567
    memo = [0] * (n + 1)
    
    for i in range(len(memo)):
        if i == 0 or i == 1:
            memo[i] = i
        else :
            memo[i] = (memo[i - 1] + memo[i - 2]) % MOD
            
    return memo[n]
