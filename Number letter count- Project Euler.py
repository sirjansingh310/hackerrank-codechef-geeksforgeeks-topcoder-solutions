word = dict()
word[1]="one"
word[2] = "two"
word[3] ="three"
word[4] = "four"
word[5] ="five"
word[6]="six"
word[7]="seven"
word[8]="eight"
word[9]="nine"
word[10]="ten"
word[11]="eleven"
word[12] = "twelve"
word[13]="thirteen"
word[14]="fourteen"
word[15] ="fifteen"
word[16]="sixteen"
word[17]="seventeen"
word[18]="eighteen"
word[19]="nineteen"
word[20]="twenty"
word[30]="thirty"
word[40]="forty"
word[50]="fifty"
word[60]="sixty"
word[70]="seventy"
word[80]="eighty"
word[90]="ninety"
word[100]="onehundred"
word[1000]="onethousand"
def gen(s):
    if s in word:
        return word[s]
    o = ""
    s = str(s)
    l = len(s)
    if l==3:
        o+=word[int(s[0])]
        o+="hundred"
        if s[1]=="0" and s[2]=="0":
            return o 
        elif s[1]=="0" and s[2]!="0":
            o+="and"
            o+=word[int(s[2])]
            return o 
        elif s[1]!="0" and s[2]=="0":
            k = int(s[1]+s[2])
            o+="and"
            o+=word[k]
            return o
        elif s[1]!="0" and s[2]!="0":
            o+="and"
            if s[1]=="1":
                k = int(s[1]+s[2])
                o+=word[k]
                return o
            k = int(s[1]+"0")
            o+=word[k]
            o+=word[int(s[2])]
            return o
    elif l==2:
       k = int(s[0]+"0")
       o+=word[k]
       o+=word[int(s[1])]
       return o
    return "not found"
                        
                        
            
            


s = ""
for i in range(1,1001):
    s+=gen(i)
print (len(s))
