import sys
sys.setrecursionlimit(10000)

def solution():
    answer = 0
    
    for i in range(h):
        for j in range(w):
            if not visited[i][j] and matrix[i][j]:
                dfs(i, j)
                answer+=1
                
    return answer


def dfs(x, y):
    visited[x][y] = 1
    
    for i in range(8):
        newX = x + dx[i]
        newY = y + dy[i]
        if 0 <= newX < h and 0 <= newY < w:
            if not visited[newX][newY] and matrix[newX][newY]:
                dfs(newX, newY)
                
    return 


while True:
    w, h = map(int, input().split())
    
    if not w and not h:
        break
    
    matrix = []
    visited = [[0] * w for row in range(h)]
    dx = [0, 1, 0, -1, 1, -1, -1, 1] 
    dy = [-1, 0, 1, 0, 1, 1, -1, -1] 

    for i in range(h):
        matrix.append(list(map(int, input().split())))
        
    print(solution())
