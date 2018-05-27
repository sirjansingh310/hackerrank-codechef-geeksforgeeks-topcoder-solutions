# https://www.hackerrank.com/challenges/building-a-list
t = int(input())
for tests in range(t):
    n = input()
    s = input()
    o = []
    length = "0"+str(len(s))+"b"
    for i in range(1<<len(s)):
        op = ""
        binary = format(i,length)
        for j in range(len(binary)):
            if binary[j]=='1':
                op+=s[j]
        if len(op)>0:
            o.append(op)
    o.sort()
    for i in o:
        print (i)
        
        
