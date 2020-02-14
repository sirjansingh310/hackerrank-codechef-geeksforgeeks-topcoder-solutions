# MOD ISSUES WITH JAVA :(
t = input()
mod = 1000000007
# https://www.rookieslab.com/posts/fast-power-algorithm-exponentiation-by-squaring-cpp-python-implementation
def fastPow(base, power, MOD):
    result = 1
    while power > 0:
        # If power is odd
        if power % 2 == 1:
            result = (result * base) % MOD

        # Divide the power by 2
        power = power // 2
        # Multiply base to itself
        base = (base * base) % MOD
    return result
for _ in range(int(t)):
    n, m = map(int, input().split())
    floorCombinations = [0] * 1001
    floorCombinations[0] = 0
    floorCombinations[1] = 1
    floorCombinations[2] = 2
    floorCombinations[3] = 4
    floorCombinations[4] = 8
     # say we have to fill for width 105
    # we filled for 104 and 1 space is left
    # we filled for 103 and 2 space is left
    # we filled for 102 and 3 space is left
    # we filled for 101 and 4 space is left
    # the total number of ways possile to fill 105 is the sum of above!. Simple dynamic programming!!
    
    for i in range(5, 1001):
        floorCombinations[i] = (
            floorCombinations[i - 1] + floorCombinations[i - 2] + floorCombinations[i - 3] + floorCombinations[i - 4]
        ) % mod
    dp = [0] * (m + 1)
    dp[0] = 0
    dp[1] = 1
    totalCases = [0] * (m + 1)
    for i in range(1, len(totalCases)):
        totalCases[i] = fastPow(floorCombinations[i], n, mod)

    # a pointer points to current cut in the wall. This basically divides the whole structure into 2 sub structures. The structure on the left is solid (dp[ptr])
    #           and the structure on the right may or may not be solid, hence the total number of combinations is considered. The product of these 2 make the number of cases
    #           which are invalid when there is a cut at ptr. Subtract this to get dp[i]
    for i in range(2, m + 1):
        dp[i] = totalCases[i]
        for ptr in range(1, i):
            dp[i] = (dp[i] - dp[ptr] * totalCases[i - ptr]) % mod
    print(dp[m] % mod)
