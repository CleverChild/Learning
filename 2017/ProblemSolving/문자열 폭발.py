x,t=input(),input()
s=[]

for i in x:
    s.append(i)
    if''.join(s[-len(t):])==t:
        del s[-len(t):]

if not s: 
    print ("FRULA")
else:
    print (''.join(s))
