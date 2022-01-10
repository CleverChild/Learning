import sys
sys.setrecursionlimit(10**6)

def solution():
    answer = 0
    
    for i in range (row):
        for j in range (column):
            if matrix[i][j] and not visited[i][j]:
                answer+=1
                dfs(i, j)

    return answer

def dfs(x, y):
    dx = [0, 1, 0, -1] 
    dy = [-1, 0, 1, 0] 
    
    visited[x][y]=1
    
    for i in range(4):
        newX= x + dx[i]
        newY= y + dy[i]
        
        if newY >= 0 and newY < column and newX >= 0 and newX < row:
            if not visited[newX][newY] and matrix[newX][newY]:
                dfs(newX, newY)

    return


T = int(input())
for i in range(T):
    
    column, row , N = input().split()
    column, row, N = int(row), int(column), int(N)
    matrix = [[0 for columnn in range(column)] for roww in range(row)]
    
    for j in range(N):
        a, b = input().split()
        matrix[int(a)][int(b)] = 1

    visited = [[0 for columnn in range(column)] for roww in range(row)]

    print(solution())
