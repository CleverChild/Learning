
def solution(s):
    answer = len(s)
    same_cnt = 1 
    prev = ""
    copy_s = ""
    
    for i in range(1, len(s)//2+1, 1):
        prev = s[0:i]    
        for j in range(i, len(s), i):
            if prev == s[j : j+i]:
                same_cnt+=1
            else:
                if same_cnt != 1:
                    copy_s += str(same_cnt) + prev
                else:
                    copy_s += prev
                same_cnt=1
            prev = s[j : j+i]
        if same_cnt != 1:
            copy_s += str(same_cnt) + s[j : ]
        else:
            copy_s += s[j : ]
        print(copy_s +", " + str(len(copy_s)))
        answer = min(answer, len(copy_s))
        same_cnt = 1        
        copy_s = ""
    return answer
    

print(solution("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))