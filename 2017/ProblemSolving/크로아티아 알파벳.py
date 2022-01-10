def LimJunSeob(t1):
    l = ['c=','c-','dz=','d-','s=','z=','lj','nj']
    global JunSeob
    global JunSeob_len

    for x in l:
        if t1.find(x) != -1:
            JunSeob = t1.find(x)
            JunSeob_len = len(x)
            return True

    return False

def delete_str():
    return "$"

a = input()
s=[]
cnt = 0

for i in a:
    s.append(i)
    t = ''.join(s)

    if LimJunSeob(t):
        tmp = JunSeob

        while tmp != len(s):
            s[tmp] = delete_str()
            tmp += 1

        cnt +=1

i = 0

for x in s:
    if x == "$":
        s[i] = ""
    
    i += 1

t = ''.join(s)

print (len(t) + cnt)