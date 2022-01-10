import queue, random

q = queue.Queue()
cache_status=[]
cache_size=3
current_size=0
refer_cnt=0
fault_cnt=0
idx=0

for i in range(19):
    refer_num = random.randint(0,10)
    print("현재시간 : %s초, 참조값 : %s"%(i,refer_num))
    if current_size < cache_size:
        cache_status.append(refer_num)
        fault_cnt+=1
        current_size+=1
    else:
        if refer_num in cache_status:
            refer_cnt+=1
        else:
            cache_status.pop(0)
            cache_status.append(refer_num)
            fault_cnt+=1
            
    print(cache_status)
    print()

print("참조횟수 : %s, 페이지 교체횟수 : %s"%(refer_cnt,fault_cnt))


