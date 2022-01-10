import math

cnt = 1

while True:
    
    try:
        r,a,h = map(int, input().split())
    except:
        break

    v = math.sqrt(math.pow(a,2) + math.pow(h,2))

    if r * 2 >= v:
        print ("Pizza",cnt,"fits on the table.")
    else:
        print ("Pizza",cnt,"does not fit on the table.")

    cnt += 1 