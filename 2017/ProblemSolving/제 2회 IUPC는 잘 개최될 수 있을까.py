a = int(input())
b, c = map(int, input().split())
d = [int(x) for x in input().split()]
i = 0
cnt = 0
total = 0

d.sort(reverse=True)

while i < a:
    if b * c <= total:
        break
    total += d[i]
    i += 1
    cnt += 1

if b * c > total:
    cnt = 0
    print ("STRESS")

if cnt:
    print (cnt)