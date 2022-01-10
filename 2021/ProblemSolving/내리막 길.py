
import sys
sys.setrecursionlimit(100000)

def solution():
    answer = dfs(0, 0)
    return answer

def dfs(x, y):
    prev = matrix[x][y]
    
    if dp[x][y]:
        return dp[x][y]

    for i in range(4):
        newX = x + dx[i]
        newY = y + dy[i]
        
        if 0 <= newX < r and 0 <= newY < c:
            if prev > matrix[newX][newY]:
                dp[x][y]+=dfs(newX, newY)
            
    if matrix[x][y] == matrix[r-1][c-1]:
        dp[x][y] += 1
        
    return dp[x][y]

# main     
r, c = input().split()
r, c = int(r), int(c) 
dp = [[0]*c for _ in range(r)]
matrix = []

for i in range(r):
    matrix.append(list(map(int, sys.stdin.readline().split())))

dx = [0, 1, 0, -1] 
dy = [-1, 0, 1, 0] 
    
print(solution())
