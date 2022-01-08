x,y,w,h = map(int, input().split())
v = min(w-x,h-y,x,y)
print (abs(v))