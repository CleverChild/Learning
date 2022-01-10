

def dfs(i, visited, computers):
    
    visited[i] = 1
    
    for j in range (len(computers)):
        
        if computers[i][j] and not visited[j]:
            dfs(j, visited, computers)
            
def solution(n, computers):
    visited = [0 for i in range(n)]
    answer = 0
    
    for i in range(n):
        if not visited[i]:
            answer+=1
            dfs(i, visited, computers)
    
    return answer

#print(solution(3, [[1, 1, 0], [1, 1, 1], [0, 1, 1]]))

# idx 1번을 체크 했을때  [1][2]==0이므로   1번과 2번 컴퓨터가 연결 안되있음. 
print(solution(3, [[1, 1, 0], [1, 1, 0], [0, 0, 1]])) 