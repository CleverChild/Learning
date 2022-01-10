import random

random_max_range=100
cache_size=40
print_cache_status=[]
cache_status = []
refer_cnt=0
page_fault=0
flag=""
current_size=0

for i in range(100):
    refer_num = random.randint(0,random_max_range)
    print("현재시간(초) : %s,     참조값 : %s"%(i,refer_num))

    if not refer_num in cache_status:    
        if current_size < cache_size:  # 적중 실패 했으나 아직  들어갈 자리가 있을 경우
            cache_status.append(refer_num)
            page_fault+=1
            current_size+=1
            flag="적중 못했으나 아직 메모리에 자리 있는 상태"
        else: #적중도 실패 했고, 들어갈 자리도 없을경우
            cache_status.pop(0)
            cache_status.append(refer_num)
            page_fault+=1
            flag="적중 실패, page fault 발생"
    else:   # 적중 성공  (참조 값이 이미 캐시에 있는 경우)
        cache_status.pop(cache_status.index(refer_num))
        cache_status.append(refer_num)
        refer_cnt+=1
        flag="적중 성공, 페이지 참조만 발생"
        
    print("현재 메모리(지워질 변수순서 반영 된 것) 상황: ",cache_status,"    ",flag)
    print()

print("page falut수 : %s , page참조만 한 횟수 : %s"%(page_fault,refer_cnt))


