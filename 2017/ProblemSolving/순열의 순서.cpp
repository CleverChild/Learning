#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <cmath>
#include <algorithm>
#include <iostream>
#include <vector>
#include <string>
using namespace std;
 
int arr[21];
bool check[21];
long long func(int num, int index)
{
    long long ret = 1;
    for (int i = num - index; i >= 1; i--)
    {
        ret *= i;
    }
    return ret;
}
 
int next_find_number(int N,int k)
{
    int count = 0;
    int i;
    for (i = 1; i <= N; i++){
        if (check[i] == false) count++;
        if (count == k) break;
    }
    return i;
}
 
int main() {
    int N;
    long long choice;
    int count = 1;
    vector<int> v;
    scanf("%d", &N);
    for (int i = 1; i <= N; i++)
        v.push_back(i);
    scanf("%lld", &choice);
    if (choice == 1){
        scanf("%lld", &choice);
        if (choice > 3){
            int index = 1;
            int find_num;
            long long number;
            while (index < N - 2)
            {
                number = func(N, index++);
                long long tmp = choice / number;
                if (choice% number == 0){
                    find_num = next_find_number(N, tmp);
                    choice = number;
                }
                else{
                    find_num = next_find_number(N, tmp + 1);
                    choice -= tmp*number;
                }
                check[find_num] = true;
                v[index - 2] = find_num;
            }
 
            for (int k = 3; k > 0; k--){
                for (int i = 1; i <= N; i++)
                {
                    if (check[i])
                        continue;
                    v[N - k] = i;
                    check[i] = true;
                    break;
                }
            }
        }
        do{
            if (count == choice) break;
            count++;
        } while (next_permutation(v.end() - 3, v.end()));
         
        for (int i = 0; i < N; i++)
        {
            printf("%d ", v[i]);
        }
    }
    else
    {
        int index = 1;
        long long ret = 1;
        int tmp;
        for (int i = 0; i < N; i++)
            scanf("%d", &arr[i]);
        for (int i = 0; i <N; i++)
        {
            tmp = arr[i];
            for (int k = 1; k <=arr[i]; k++){
                if (check[k] == true)
                    tmp--;
            }
            long long number = func(N, index++);
            ret += (tmp - 1)*number;
            check[arr[i]] = true;
        }
        printf("%lld", ret);
    }
}