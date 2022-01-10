import math
a, b, c = map(int,input().split())

n1 = c / b
n2 = math.pow (n1 , 2) + 1
n3 = math.pow(a, 2) / n2
Final_b = math.sqrt(n3)
Final_a = n1 * Final_b

print (int(Final_b),int(Final_a))