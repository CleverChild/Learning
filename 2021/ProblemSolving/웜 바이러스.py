import sys

def dfs(v, currentWormCheckingPC):
    cnt=0
    currentWormCheckingPC[v]=1

    for i in range(1, n+1):
        if not currentWormCheckingPC[i] and (computers[i][v] or computers[v][i]):
            cnt+=1
            cnt+=dfs(i, currentWormCheckingPC)
            
    return cnt

def solution():
    answer = 0
    currentWormCheckingPC = [0 for _ in range(n+1)]
    answer=dfs(1, currentWormCheckingPC)
    return answer
    

n = int(sys.stdin.readline())  # 컴터 번호 몇 번까지 인지
m = int(sys.stdin.readline())  # 연결 쌍

computers = [[0 for col in range(n+1)] for row in range(n+1)]

for _ in range (m):
    a, b = input().split()
    computers[int(a)][int(b)] = 1
    
print(solution())
    