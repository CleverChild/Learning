from matplotlib import pyplot as plt
import random
cnt=0
n=200
for i in range (n):
    x=random.random()
    y=random.random()
    if x *x + y *y <=1:
        cnt+=1
        plt.scatter(x,y,c='r',marker ='.')
    else:
        plt.scatter(x,y,c='b',marker ='.')
    plt.pause(0.001)
print((cnt /n)*4)
plt.show()
