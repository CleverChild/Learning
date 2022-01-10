a, b, v = map(int, input().split())

n1 = v - b
n2 = a - b
n3 = n1 // n2

if n2 == 1 or not n1 % n2:
	print (n3)
else:
	print (n3 + 1)