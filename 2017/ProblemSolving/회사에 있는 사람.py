save_s = set()
answer = ""

for i in range (int(input())):
    tmp = input()
    answer = tmp[:-6]

    if "enter" in tmp:
        save_s.add(answer)
    else:
        save_s.remove(answer)
        
for x in sorted (save_s, reverse = True):
    print (x)