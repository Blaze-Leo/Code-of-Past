from re import L


def sorts(L):
    for i in range(1,len(L)):
        j=0
        while j<(len(L)-i):
            if L[j]> L[j+1]:
                L[j], L[j+1]=L[j+1],L[j]
            j+=1
    print(L)

if __name__ =='__main__':
    l=[5,45,56,34,64,14,46,124,467,3]
    sorts(l)