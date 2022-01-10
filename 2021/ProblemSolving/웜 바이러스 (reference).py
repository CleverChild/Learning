#2606

F = int(input())
C = int(input())
W = [[] for i in range(F+1)]

for i in range(C):
    a,b = map(int, input().split())
    W[a].append(b)
    W[b].append(a)

arr = []
def DFS(x):
    arr.append(x)
    for i in W[x]:
        if i not in arr:
            DFS(i)
DFS(1)    
print(len(arr)-1)  