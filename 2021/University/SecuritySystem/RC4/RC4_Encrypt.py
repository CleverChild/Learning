S = [i for i in range(0,256)]
K=[]
userKey ="Maple"
plainText ="TodayWeAreAttack!"
cipherText=[]
keyStream=[]
saveKeyStream=[]
j=0

for i in range(256):    
    K.append(userKey[i % len(userKey)])

for i in range(256):
    j = (j + S[i] + ord(K[i])) % 256
    S[i], S[j] = S[j], S[i]

i=j =0

#여기서부터 K[256]안씀
#making keyStream

#  흘려보내기. 
for counter in range (0,256):
    i = (i +1) % 256
    j = (j + S[i]) % 256
    S[i], S[j] = S[j], S[i]
    t = (S[i] + S[j]) % 256
    #keyStream.append(S[t])
    
#  keyStream 생성. 
for x in plainText:
    i = (i +1) % 256
    j = (j + S[i]) % 256
    S[i], S[j] = S[j], S[i]
    t = (S[i] + S[j]) % 256
    keyStream=S[t]
    saveKeyStream.append(keyStream)
    cipherText.append(keyStream ^ ord(x))

print("Encrypted Text : ",end ='')
for x in cipherText:
    print(chr(x),end ='')