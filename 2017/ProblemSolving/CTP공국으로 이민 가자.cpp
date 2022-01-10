#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void processN(int num);
void processC(int num);

int main(void)
{
	int num,cnt,i;
	char select[10];


	scanf("%d",&num);
	//scanf("%d",&cnt);
	//scanf("%s",select);

while(num){

	scanf("%d",&cnt);
        scanf("%s",select);

	if(!(strcmp("N",select)))  // num --> str
	{
		processN(cnt);
	}
	else
	{
		processC(cnt);
	}

	num--;
	}

	return 0;
}

void processN(int cnt) //   num --> str
{
	int i,num;
	char str[]="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	for(i=0;i<cnt;i++)
	{
		scanf("%d",&num);
		printf("%c ",str[num-1]);
	}
	puts("");

}

void processC(int cnt)  //   str -->   num
{
	int i;
	char input[502];
			
	for(i=0;i<cnt;i++)
	{
		scanf("%s",&input[i]);
		printf("%d ",input[i]-64);
	}

	puts("");
}
