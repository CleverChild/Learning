import random, math

def poisson(mean, n):
    result = (math.pow(2.718281,-mean)) * (math.pow(mean,n)) / math.factorial(n)
    return result

def get_tpump(current_time, total_arrive_now):
    # res is service time.
    res=0

    # range함수 특성상  +1해줘야함.
    total_arrive_now+=1
    
    for my_car in range(total_arrive_now):
        res+=poisson(current_time*prarr,my_car)*average_mean

    return res


def gaussianRandom(average, stdev):

    res=0

    for i in range(100):
        r1 = random.random()
        r2 = random.random()
        
        z1 = math.sqrt(-2 * math.log(r1)) * math.cos(2 * 3.14159265 * r2)
        z2 = math.sqrt(-2 * math.log(r1)) * math.sin(2 * 3.14159265 * r2)
        
        num = (average * 0.01) + (stdev * z1 * math.sqrt(0.01))
        res+=num
    return res

  
arrive=0
prarr=0.43333333
tpump=[0,0]
queue=0
total_queue=0
total_arrive=0
tlimit=45
average_mean=5.5
avg_liter=65
stdev=4


for i in range(1,tlimit+1):
    arrive=0
    if(random.random() < prarr):   #차량 도착.
        arrive=1        
        queue+=1
        total_arrive+=1

    for j in range(0,len(tpump)):
        
        if(tpump[j]>0):
            tpump[j]-=1
        elif(tpump[j]<=0):
            tpump[j]=0

        if(tpump[j]==0 and queue!=0):  #빈자리 발생.
            liter = gaussianRandom(avg_liter,stdev)
            tpump[j]=get_tpump(i,total_arrive) * (1/8) * (liter)
            tpump[j]=math.ceil(tpump[j])
            queue-=1

    total_queue+=queue

    print("time : %s, arrive : %s, queue : %s , tpump : %s"%(i,arrive,queue,tpump))


print("total arrive : %s, total queue : %s"%(total_arrive,total_queue))
print("줄을 서서 기다리는 고객 평균 수 : %4f"%(total_queue/tlimit))
print("평균 대기 시간 : %4f"%(total_queue/total_arrive))

        
