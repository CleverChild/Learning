n = int(input())
graph = []
visited = [[0 for col in range(n)] for row in range(n)]
dx = [0, 1, 0, -1] 
dy = [-1, 0, 1, 0]  
answer_lst=[]

for i in range(n):
    graph.append(list(map(int, input())))
    
def dfs(i, j):
    
    cnt = 0
    
    if not visited[i][j]:
        visited[i][j] = 1
        cnt = 1
    
    for k in range(4):
        newX = j + dx[k]
        newY = i + dy[k]
        
        if newX >= 0 and newY >=0 and newX < n and newY < n:
            if not visited[newY][newX] and graph[newY][newX]:
                cnt+=dfs(newY, newX)
    
    return cnt
    

for i in range (n):
    for j in range(n):
        if not visited[i][j] and graph[i][j]:
            answer_lst.append(dfs(i,j))

print(len(answer_lst))            
            
for answer in sorted(answer_lst):
    print(answer)