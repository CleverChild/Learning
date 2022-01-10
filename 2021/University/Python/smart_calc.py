import os

operator=["+","-","*","/","=",]

def string_calculator(user_input,show_history=False):
    string_list=[]
    lop=0

    if user_input[-1] not in operator:  #이 문장이 있어야 맨 마지막 숫자가 찍히게됨.
        user_input+="="     # 오퍼레이터가 있어야 숫자들이 lst에 들어가기 때문에. 

    for i, s in enumerate(user_input):
        print(i,s)
        if s in operator:
            if user_input[lop:i].strip()!="":
                string_list.append(user_input[lop:i])  # 숫자에 해당하는 부분을 append
                string_list.append(s)  #  연산자를  넣음
                lop = i + 1    # oper포지션 i만큼 증가

    string_list = string_list[:-1] # 맨 마지막 문자(=) 제거

    #print(string_list)
            

# ['100','+','200','-','300','/','200']

    pos = 0  #어느위치를 계산하고 있는지 기억하는 변수. 

    while True:
        if len(string_list) == 1:   # pos+1 > len(string_list) 이거랑 똑같.
            break
        if ( len(string_list) > pos + 1 ) and (string_list[pos] in operator):
            temp = string_list[pos-1] + string_list[pos] + string_list[pos+1]  # 100 + 200 
            del string_list[0:3]  # temp에 저장후 지움.
            string_list.insert(0,str(eval(temp)))  #  100 +200 지우고, index 0에 다시 집어 넣지만, 100+200을 계산후에 집어넣음. 
            pos = 0  # 새로운 계산 값으로 바꿔치기 했기 떄문에, 다시 0으로 복귀.

            if show_history:
                print(string_list)
                
        pos += 1  # 현재 pos가 연산자가 아닐경우  하나씩 증가하면서 다음 index 확인해야. 
        

    if len(string_list) > 0:
        result = float(string_list[0])

    return round(result,4)

while True:
    #os.system("cls")
    user_input=input("계산식 입력 : ")
    if user_input =="exit":
        break

    result = string_calculator(user_input,show_history=True)
    print("결과 : {}".format(result))
    #os.system("pause")

    
