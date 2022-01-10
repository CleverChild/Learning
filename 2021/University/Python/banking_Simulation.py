import random

print("{0} {1} {2} {3} {4} {5}".format("하한값","상한값","처음월급","처음저축액","은행이자","시드값 입력 :"))
perlow, perhigh, salary, savings, perint, seed = input().split(" ")

perlow, perhigh = float(perlow), float(perhigh)
salary , savings = float(salary), float(savings)
perint, seed =  float(perint), int(seed)

seed = int(seed)
random.seed(seed)

print("\n%s %s %s %s %0.8f %s %0.8f"%("SALARY","INCREASE","RATE","IS",perlow,"-",perhigh))
print("%s %s %s %s %0.8f\n"%("INTEREST","RATE","ON","SAVINGS",perint))
print("{0}    {1}    {2}     {3}".format("YEAR","SALARY.INC.RATE","SALARY","SAVINGS"))
print("%2d           %0.3f       %0.3f     %0.3f"%(0,0.00,salary,savings))

for year in range(30):
    my_random = random.random()
    perinc = ((perhigh-perlow) * my_random) + perlow
    savings = savings + (perint*savings + 0.1*salary)
    salary = salary + perinc*salary
    if (year+1) % 5==0:
        print("%2d           %0.3f       %0.3f     %0.3f"%(year+1,perinc,salary,savings))

        
