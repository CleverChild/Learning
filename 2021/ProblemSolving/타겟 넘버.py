from collections import deque

# BFS 
def solution(numbers, target):
    answer = 0
    tmp = deque([0])
    save_sum = []
    for n in numbers:
        save_sum = []  # tmp에는 [2,0,0,-2] 까지만 들어가게 하려고 [1,-1]부분을 삭제함, 
                       # 이미 계산된 부분은 지우는 것.
        while tmp:
            num = tmp.popleft()
            cu1 = num + n
            cu2 = num - n
                
            save_sum.append(cu1)
            save_sum.append(cu2)
        tmp = deque(save_sum[:])  # 지금방금 연산한 과정들을 tmp에 저장해서 다음 루프떄 pop연산 할 수 있게함. 
    answer = tmp.count(target) #  반복문을 모두 돌면 tmp  list에는  마지막 연산결과들이 저장되있음. 
    return answer