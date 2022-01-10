S = [i for i in range(0,256)]
K=[]
cipherText=[]
keyStream=[]
saveKeyStream=[]
originalPlainText=[]
cipherText="¥æ¦Õ4vMÅÚ4zÌ"
userKey="Maple“

def decodeFunc ():
    
    i=j=0

    for i in range(256):    
        K.append(userKey[i % len(userKey)])
        
    for i in range(256):
        j = (j + S[i] + ord(K[i])) % 256
        S[i], S[j] = S[j], S[i]
    
    i=j=0

    #흘려버리기
    for counter in range (0,256):
        i = (i +1) % 256
        j = (j + S[i]) % 256
        S[i], S[j] = S[j], S[i]
        t = (S[i] + S[j]) % 256
    
    for x in cipherText:
        i = (i +1) % 256
        j = (j + S[i]) % 256
        S[i], S[j] = S[j], S[i]
        t = (S[i] + S[j]) % 256
        keyStream=S[t]
        saveKeyStream.append(keyStream)
        originalPlainText.append(keyStream ^ ord(x))
    
    print("Decrypted Text : ",end ='')
    
    for x in originalPlainText:
        print(chr(x),end ='')
        print()
        print("CipherText : ",cipherText)
        print("UserKey : ",userKey)

decodeFunc()