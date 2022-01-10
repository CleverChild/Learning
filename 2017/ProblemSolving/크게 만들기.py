n, k = [int(x) for x in input().split()]
num = input()

answer = []
deleted_count = 0
for digit in num:
    while deleted_count < k and answer and int(digit) > int(answer[-1]):
        answer.pop()
        deleted_count += 1
    if len(answer) < n - k:
        answer.append(digit)
    else:
        deleted_count += 1
print(''.join(answer))