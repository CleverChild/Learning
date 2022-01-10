import random

side = [0]*6

nroll, seed = input().split(" ")
nroll, seed = int(nroll),int(seed)

random.seed(seed)

for i in range(nroll):
    side[random.randrange(0,6)]+=1


print(side)
