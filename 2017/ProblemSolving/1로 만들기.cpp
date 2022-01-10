#include <stdio.h>
 
int N;
int dyn[10000001];
int solve(int num) {
    if (dyn[num] != -1)
        return dyn[num];
 
    int value = 2147483647;
    if (num % 3 == 0) {
        int tempValue = solve(num / 3);
        value = (value > tempValue) ? tempValue : value;
    }
    if (num % 2 == 0) {
        int tempValue = solve(num / 2);
        value = (value > tempValue) ? tempValue : value;
    }
    int tempValue = solve(num - 1);
    value = (value > tempValue) ? tempValue : value;
 
    dyn[num] = value + 1;
    return dyn[num];
}
int main() {
    int N;
    scanf("%d", &N);
 
    for (int i = 0; i < 10000001; i++)
        dyn[i] = -1;
    dyn[1] = 0;
    printf("%d\n", solve(N));
 
    return 0;
}
