#include <stdio.h>
#include <math.h>
#include <string.h>

long long gcd(long long n1, long long n2);

int main(void)
{
	long long a,b,i;
	long long result,result2;
	long long num; // b-a

	scanf("%lld %lld",&a,&b);

	num=b-a;

	//printf("%lld\n",(long long)sqrt(a));
	//printf("%lld\n",(long long)sqrt(b));
	
	result=(long long)sqrt(b)-(long long)sqrt(a);

	if(!result)
	{
		puts("0");
		return 0;
	}

	result2=gcd(result,b-a);

	if(result2==1)
	{
		printf("%lld/%lld\n",result,num);
	}
	else
	{
		result2=gcd(result,b-a);
		printf("%lld/%lld\n",result/result2,num/result2);
	}

	return 0;
}

long long gcd(long long n1, long long n2)
{
    long long temp;
    if (n1 < n2) {
        temp = n1;
        n1 = n2;
        n2 = temp;
    }
    while (n2 != 0) {
        temp = n1%n2;
        n1 = n2;
        n2 = temp;
    }
	
    return n1;
}

