n = int(input())
cnt = 0
r = 1

if not n%2 or not n%5:
    print (-1)
else:
    while r % n:
        r *= 10
        r += 1
        r %= n
        cnt += 1
    
    print (cnt + 1)