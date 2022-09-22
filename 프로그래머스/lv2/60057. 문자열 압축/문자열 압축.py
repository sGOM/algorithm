def solution(s):
    min = len(s)
    for i in range(1, len(s)//2 + 1):
        cur = len(s)
        token = s[:i]
        repeated = 1
        for t in range(1, len(s)//i):
            if token == s[i*t:i*(t+1)]:
                cur -= i
                repeated += 1
            else :
                token = s[i*t:i*(t+1)]
                if 1 < repeated:
                    cur += len(str(repeated))
                    repeated = 1
        if 1 < repeated:
            cur += len(str(repeated))
        if cur < min :
            min = cur
                
    return min