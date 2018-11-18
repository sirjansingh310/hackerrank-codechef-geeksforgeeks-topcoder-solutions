# https://projecteuler.net/problem=24
def result(n):
    available = [0,1,2,3,4,5,6,7,8,9]
    facts = [1,1,2,6,24,120,720,5040,40320,362880]
    value = ""
    index = n-1 # index 0 => 0123456789
    for i in range(9,-1,-1):
        q = index//facts[i]
        value += str(available[q])
        available.pop(q)
        index = index%facts[i]
        i-=1 
    return value


print (result(pow(10,6)))
