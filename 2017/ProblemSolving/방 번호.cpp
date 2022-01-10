#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>
#include <vector>

int f (int arr[]);
void processF (int *arr);

int main(void)
{
	char str[1000004] = {'\0'};
	int i=0,cnt=0,data[10]={ 0 };

	scanf("%s",str);

	while(str[i])
	{
		if(str[i]=='9')
		{
			str[i]='6';
		}
		data[(str[i]-48)]++;
		i++;
	}
	i=0;
	while(1)
	{
		if(!data[i])
		{
			i++;
			continue;
		}
		processF(data);
		cnt++;
		i=0;
		if(f(data)) break;
		continue;
	}
	printf("%d\n",cnt);
	return 0;
}

int f (int arr[])  //data가 전부 0이하 인지만 확인하는 함수.
{
	int i;

	for(i=0;i<10;i++)
	{
		if(arr[i]>0)
		{
			return 0;
		}
	}
	return 1;
}

void processF (int *arr)
{
	int i;

	for(i=0;i<10;i++)
	{
		if(i==6)
		{
			arr[i]-=2;
		}
		else
		{
			arr[i]--;
		}
	}
}