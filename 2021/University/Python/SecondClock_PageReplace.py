def findAndUpdate(num,cache_size):
    for j in range (cache_size):
            if cache_status[j]==num:
                save_SC[j]=1

                # 이미 캐시에 저장된 값이 num(참조값)으로 왔기  때문에,
                # replace할 필요 없는경우
                # false반환 해주면, 포인터 이동 없음.
                return False

    # replace해야 할 경우 
    return True


def replaceAndUpdate(num,indi):
    while True:
        if save_SC[indi]==0:   # save마킹 되있지 않은 경우니까, 교체 대상임.
            cache_status[indi]=num
            return (indi+1) % cache_size
        
        # save마킹 되어 있을 경우, save시키고나서, save마킹 지워버림.
        save_SC[indi]=0
        indi = (indi+1) % cache_size
        #return  (indi+1) % cache_size

import random

random_max_range=9
cache_size=7
cache_status = []
save_SC=[0]*cache_size
refer_cnt=0
page_fault=0
quit_cnt=0
current_size=0
indicator=0
#seq=[7,2,8,8,5,8,5,1,3,7,6,1,4,8,5,6,6,9,4]
#seq2=[7,2,8,8,7,2,8,7,2,8,6,1,4,8,5,6,6,9,4] [1,1,1] 포인터 위치0
#seq3=[7,2,8,2,9,4,2,1,3,7,3,2,1,3,2,1,7,9,4]

for i in range(19):
    refer_num = random.randint(0,random_max_range)
    #refer_num=seq3[i]
    print("현재시간(초) : %s,     참조값 : %s"%(i,refer_num))

   
    if current_size < cache_size:
        while refer_num in cache_status:
            refer_num=random.randint(0,random_max_range)
        cache_status.append(refer_num)
        page_fault+=1
        current_size+=1
    else:
        if findAndUpdate(refer_num,cache_size):
            indicator = replaceAndUpdate(refer_num,indicator)
            page_fault+=1
        else:
            refer_cnt+=1
            
        
    print("메모리 : %s, 포인터 : %s, 참조변수 : %s"%(cache_status,indicator,save_SC))
    print()

print("page_fault : %s, refer_cnt : %s"%(page_fault,refer_cnt))    
        
