import sys
sys.setrecursionlimit(10000)

def solution():
    answer = 0
    
    for i in range(m):
        a, b = map(int,  sys.stdin.readline().split())
        graph[a].append(b)
        graph[b].append(a)
        
    for i in range(1, len(visited)):
        if not visited[i]:
            answer+=1
            dfs(i)
            
    return answer

def dfs(v):
    visited[v]=1
    
    for i in graph[v]:
        if not visited[i]:
            dfs(i)
        
    return 

# main
n, m = map(int, input().split())
graph = [[] for i in range(n+1)]
visited = [0 for i in range(n+1)]

print(solution())
