import numpy

def make_lotto_number(**kwargs):
    
    rand_num = numpy.random.choice(range(1,46),6,replace=False)
    rand_num.sort()

    lotto=[]

    if kwargs.get("include"):
        include = kwargs.get("include")
        lotto.extend(include)

        cnt_make = 6 -len(lotto)

        for _ in range(cnt_make):
            for j in rand_num:
                if lotto.count(j)==0:
                    lotto.append(j)
                    break
    else:
        lotto.extend(rand_num)
        #print(" lotto : ",lotto)
        

    if kwargs.get("exclude"):
        exclude=kwargs.get("exclude")
        lotto = list(set(lotto) - set(exclude))
        
        while len(lotto) != 6:
            for i in range(6 - len(lotto)):
                rand_num = numpy.random.choice(range(1,46),6,replace=False)
                rand_num.sort()

                for j in rand_num:
                    if ( lotto.count(j)==0 ) and ( j not in exclude ):
                        lotto.append(j)
                        break
                    
    if kwargs.get("continuty"):
        continuty =  kwargs.get("continuty")
        start_num = numpy.random.choice(lotto,1)

        seq_num = []

        for i in range(start_num[0],start_num[0] + continuty):
            seq_num.append(i)

        seq_num.sort()
        cnt_make = 6 - len(seq_num)
        lotto=[]
        lotto.extend(seq_num)
        
        while len(lotto) != 6:
            for _ in range(6 - len(lotto)):
                rand_num = numpy.random.choice(range(1,46),6,replace=False)
                rand_num.sort()

                for j in rand_num:
                    if ( lotto.count(j)==0 ) and ( j not in seq_num ):
                        lotto.append(j)
                        break
                    
                lotto = list(set(lotto))   
        
    lotto.sort()
    return lotto


count = int(input("아무 조건 없는 로또 번호 몇 개 생성?  "))
print()
for j in range(count):
    print("아무런 조건도 적용되지 않는 로또번호 : ",make_lotto_number())
    
print()
print("include 적용된 로또 : ",make_lotto_number(include=[3,14,19,36]))
print("exclude 적용된 로또 : ",make_lotto_number(exclude=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36]))
print()
print("continuty(3) 적용된 로또 : ",make_lotto_number(continuty=3))
print("continuty(4) 적용된 로또 : ",make_lotto_number(continuty=4))
print()

