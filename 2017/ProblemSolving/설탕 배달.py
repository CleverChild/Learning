num = input()
flag=1

if int(num)%5!=0 and int(num)!=6 and int(num)!=9 and int(num)!=12:
  if int(num)%5==3:
    print(int(int(num)/5+1))

  else:
    i=int(num)/5

    while int(i) > 0: 
      save=int(num)-(5*int(i))
      i-=1

      if int(save)%3==0:
        i+=1
        print(int(int(i)+(int(save)/3)))
        flag=0
        break

    if int(flag):
        print(-1)

elif int(num)%5==0:
    print(int(int(num)/5))

elif int(num)==6 or int(num)==9 or int(num)==12:
    print(int(int(num)/3))