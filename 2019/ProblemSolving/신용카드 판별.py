sum=0

def sum_digit (c):
    total = 0
    for i in (str(c)):
        total = total + int(i)
    return total

a = int(input())

for i in range (a):
    b = input()
    res = b[::-1]
    res2 = res[1::2]   #카드번호  짝수 번째 수
    res3 = res[0::2]  #카드번호  홀수 번째 수

    for j in res2:
        if(int(j) * 2  >= 10):
            sum += sum_digit(int(j) * 2)
        else:
            sum += 2*int(j)

    for j in res3:
        sum+=int(j)

    if(sum % 10 == 0):
        print("T")
    else:
        print("F")
    sum=0
