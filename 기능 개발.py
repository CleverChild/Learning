import math

def solution(progresses, speeds):
    
    answer = []
    workTime = []
    idx=0
    cnt = 1
    
    for i in progresses:
        j=speeds[idx]
        workTime.append(math.ceil((100-i)/j))
        idx+=1
        
    prev = workTime[0]
        
    for i in range(1, len(workTime)):
        if prev >= workTime[i]:
            cnt+=1
        else:
            answer.append(cnt)
            cnt=1
            prev = workTime[i]
        
    answer.append(cnt)
    return answer


#print(solution([40, 93, 30, 55, 60, 65], [60, 1, 30, 5, 10, 7]))
print(solution([20, 99, 93, 30, 55, 10], [5, 10, 1, 1, 30, 5]))