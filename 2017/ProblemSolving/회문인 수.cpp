#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>
#include <vector>

int f (int num,int i);
char * mystrrev(char *s);

int main(void)
{
	int num,i,flag=1;
	int a,q;

	scanf("%d",&a);
	for(q=0;q<a;q++)
	{
		scanf("%d",&num);
		for(i=2;i<65;i++)
		{
			if(f(num,i))
			{
				puts("1");
				flag=0;
				break;
			}
		}
		if(flag) puts("0");
		flag=1;
	}
	return 0;
}

int f (int num,int select)
{
	char str[65] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@";
	char straight[100000]= {0};
	char reverse[100000] = {0};
	int idx=0;

	while(num)
	{
		straight[idx++]=str[num%select];
		num/=select;
	}
	strcpy(reverse,straight);
	if(strcmp(reverse,mystrrev(straight))==0)
	{
		return 1;
	}
	return 0;
}

char * mystrrev(char *s)
{
  char tmp;
  int len=strlen(s);
  for(int i=0; i<len/2;++i)
  {
    tmp=*(s+i);
    *(s+i)=*(s+len-1-i);
    *(s+len-1-i)=tmp;
  }
  return s;
}